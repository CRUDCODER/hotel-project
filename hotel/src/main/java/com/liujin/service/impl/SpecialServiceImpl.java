package com.liujin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.Room;
import com.liujin.entity.Special;
import com.liujin.mapper.SpecialMapper;
import com.liujin.service.ISpecialService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author liujin
 * @date created in 2020/2/2 13:03
 */
@Service
public class SpecialServiceImpl
    extends ServiceImpl<SpecialMapper, Special>
    implements ISpecialService
{
    /**
     * 查询出结果之前 修改过期特价的信息
     * @return
     */
    @Override
    public List<Special> fetchSpecial() {
        List<Special> notIsTodaySpecial = getBaseMapper().notIsTodaySpecial(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        if (notIsTodaySpecial!=null){
            for (Special special : notIsTodaySpecial) {
                update(new UpdateWrapper<Special>().set("is_show",0).eq("special_id",special.getSpecialId()));
            }
        }
        List<Special> specials = getBaseMapper().fetchSpecial();
        return specials;
    }

    /**
     * 添加特价房
     * 默认状态为不显示 0:不显示  1：显示
     * @param special
     * @return
     */
    @Override
    public boolean increaseSpecialRoom(Special special) {
        special.setSpecialDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        special.setIsShow(1);
        if (save(special)){
            return true;
        }
        return false;
    }

    @Override
    public List<Room> selectTodayIsSpecial() {
        return getBaseMapper().selectTodayIsSpecial();
    }
}

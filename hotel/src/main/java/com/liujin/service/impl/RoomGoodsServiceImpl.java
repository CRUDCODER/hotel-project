package com.liujin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.Goods;
import com.liujin.entity.GoodsOutbound;
import com.liujin.entity.RoomGoods;
import com.liujin.mapper.RoomGoodsMapper;
import com.liujin.service.IGoodsOutboundService;
import com.liujin.service.IGoodsService;
import com.liujin.service.IRoomGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/29 19:18
 */
@Service
public class RoomGoodsServiceImpl extends ServiceImpl<RoomGoodsMapper,RoomGoods> implements IRoomGoodsService {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IGoodsOutboundService goodsOutboundService;
    @Override
    public List<RoomGoods> fetchRoomGoods() {
        return getBaseMapper().fetchRoomGoods();
    }

    /**
     * 暂时保留  还需对商品库存进行减少  插入一条入库记录
     * @param roomGoods
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean increaseRoomGoods(RoomGoods roomGoods) {
        if (roomGoods.insert()){
            //首先查出该商品信息 判断商品数量是否充足  否则抛出异常进行回滚
            Goods good = goodsService.getById(roomGoods.getGoodsId());
            if (good.getGoodsCount()<=0){
                throw new RuntimeException("商品暂时没有库存,请联系管理人员!");
            }
            if (good.getGoodsCount()<roomGoods.getGoodsCount()){
                throw new RuntimeException("商品库存不足,请核实所需配置商品数量!");
            }
            Goods goods=new Goods();
            goods.setGoodsId(roomGoods.getGoodsId());
            goods.setGoodsCount(roomGoods.getGoodsCount());
            goodsService.modifyGoodsCount(goods);
            //往出库表中插入记录
            GoodsOutbound goodsOutbound=new GoodsOutbound();
            goodsOutbound.setRoomGoodsId(roomGoods.getRoomGoodsId());
            goodsOutbound.setOutboundDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            goodsOutbound.setOutboundCount(roomGoods.getGoodsCount());
            goodsOutboundService.increaseGoodsOutbound(goodsOutbound);
            return true;
        }
        return false;
    }

    @Override
    public String fetchGoodsByRoomNumber(RoomGoods roomGoods) {
        List<RoomGoods> roomGoods1 = getBaseMapper().fetchGoodsByRoomNumber(roomGoods);
        if (roomGoods1.size()!=0){
            StringBuffer stringBuffer=new StringBuffer();
            for (RoomGoods goods : roomGoods1) {
                stringBuffer.append(goods.getGoodsName()+"x"+goods.getGoodsCount()+"、");
            }
            String substring = stringBuffer.substring(0,stringBuffer.length()-1);
            return substring;
        }
        return "该房间还未配备物资,请联系前台!";
    }
}

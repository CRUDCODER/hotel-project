package com.liujin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.Goods;
import com.liujin.entity.GoodsStorage;
import com.liujin.mapper.GoodsStorageMapper;
import com.liujin.service.IGoodsService;
import com.liujin.service.IGoodsStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/30 9:28
 */
@Service
public class GoodsStorageServiceImpl extends ServiceImpl<GoodsStorageMapper, GoodsStorage> implements IGoodsStorageService {
    @Autowired
    private IGoodsService goodsService;

    @Override
    public List<GoodsStorage> fetchGoodsStorage(GoodsStorage goodsStorage) {
        return getBaseMapper().fetchGoodsStorage(goodsStorage);
    }

    /**
     * 入库商品成功之后 需要修改商品总库存
     * @param goodsStorage
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean increaseGoodsStorage(GoodsStorage goodsStorage) {
        goodsStorage.setStorageDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        System.out.println(goodsStorage);
        if (goodsStorage.insert()) {
            Goods goods=new Goods();
            goods.setGoodsId(goodsStorage.getGoodsId());
            goods.setGoodsCount(goodsStorage.getStorageCount());
            goodsService.increaseGoodsCount(goods);
            return true;
        }
        return false;
    }
}

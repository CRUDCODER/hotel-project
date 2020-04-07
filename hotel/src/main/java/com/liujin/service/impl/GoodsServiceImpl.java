package com.liujin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.Goods;
import com.liujin.mapper.GoodsMapper;
import com.liujin.service.IGoodsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/29 18:38
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    @Override
    public List<Goods> fetchGoodsInfo(Goods goods) {
        return getBaseMapper().fetchGoodsInfo(goods);
    }

    @Override
    public boolean modifyGoodsCount(Goods goods) {
        return getBaseMapper().modifyGoodsCount(goods);
    }

    @Override
    public boolean increaseGoodsCount(Goods goods) {
        System.out.println(goods);
        return getBaseMapper().increaseGoodsCount(goods);
    }
}

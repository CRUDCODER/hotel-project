package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.Goods;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/29 18:37
 */
public interface GoodsMapper extends BaseMapper<Goods> {


    List<Goods> fetchGoodsInfo(Goods goods);

    boolean modifyGoodsCount(Goods goods);

    boolean increaseGoodsCount(Goods goods);
}

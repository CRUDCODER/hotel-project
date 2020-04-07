package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.Goods;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/29 18:38
 */
public interface IGoodsService extends IService<Goods> {

    List<Goods> fetchGoodsInfo(Goods goods);
    boolean modifyGoodsCount(Goods goods);
    boolean increaseGoodsCount(Goods goods);
}

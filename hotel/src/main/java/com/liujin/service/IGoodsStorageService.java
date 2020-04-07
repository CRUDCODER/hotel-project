package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.GoodsStorage;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/30 9:28
 */
public interface IGoodsStorageService extends IService<GoodsStorage> {
    List<GoodsStorage> fetchGoodsStorage(GoodsStorage goodsStorage);

    boolean increaseGoodsStorage(GoodsStorage goodsStorage);
}

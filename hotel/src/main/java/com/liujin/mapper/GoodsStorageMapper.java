package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.GoodsStorage;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/30 9:27
 */
public interface GoodsStorageMapper extends BaseMapper<GoodsStorage> {

    List<GoodsStorage> fetchGoodsStorage(GoodsStorage goodsStorage);
}

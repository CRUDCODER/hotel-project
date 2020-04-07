package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.GoodsOutbound;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/30 10:21
 */
public interface GoodsOutboundMapper extends BaseMapper<GoodsOutbound> {

    List<GoodsOutbound> fetchGoodsOutbound(GoodsOutbound goodsOutbound);

}

package com.liujin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.GoodsCategory;
import com.liujin.mapper.GoodsCategoryMapper;
import com.liujin.service.IGoodsCategoryService;
import org.springframework.stereotype.Service;

/**
 * @author liujin
 * @date created in 2020/1/29 14:40
 */
@Service
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory> implements IGoodsCategoryService {
}

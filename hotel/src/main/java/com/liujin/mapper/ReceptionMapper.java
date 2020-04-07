package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.Reception;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/1/31 10:27
 */
public interface ReceptionMapper extends BaseMapper<Reception> {
List<Reception> fetchReception(Reception reception);
}

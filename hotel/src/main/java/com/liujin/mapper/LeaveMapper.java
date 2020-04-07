package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.Leave;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/2/2 9:22
 */
public interface LeaveMapper extends BaseMapper<Leave> {


    List<Leave> fetchLeave(Leave leave);

    boolean insertLeave(Leave leave);
}

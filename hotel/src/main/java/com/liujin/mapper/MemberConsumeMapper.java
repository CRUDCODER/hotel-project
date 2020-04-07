package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.MemberConsume;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/2/1 9:49
 */
public interface MemberConsumeMapper extends BaseMapper<MemberConsume> {


    List<MemberConsume> fetchMemberConsume(MemberConsume memberConsume);
}

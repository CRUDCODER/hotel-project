package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.MemberConsume;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/2/1 9:49
 */
public interface IMemberConsumeService extends IService<MemberConsume>  {

    List<MemberConsume> fetchMemberConsume(MemberConsume memberConsume);
}

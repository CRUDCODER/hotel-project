package com.liujin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.MemberConsume;
import com.liujin.mapper.MemberConsumeMapper;
import com.liujin.service.IMemberConsumeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/2/1 9:49
 */
@Service
public class MemberConsumeServiceImpl extends
        ServiceImpl<MemberConsumeMapper, MemberConsume>
        implements IMemberConsumeService {
    @Override
    public List<MemberConsume> fetchMemberConsume(MemberConsume memberConsume) {
        return getBaseMapper().fetchMemberConsume(memberConsume);
    }
}

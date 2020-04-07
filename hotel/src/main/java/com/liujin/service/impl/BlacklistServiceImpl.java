package com.liujin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujin.entity.Blacklist;
import com.liujin.mapper.BlacklistMapper;
import com.liujin.service.IBlacklistService;
import org.springframework.stereotype.Service;

/**
 * @author liujin
 * @date created in 2020/2/2 13:05
 */
@Service
public class BlacklistServiceImpl
    extends ServiceImpl<BlacklistMapper, Blacklist>
    implements IBlacklistService {
}

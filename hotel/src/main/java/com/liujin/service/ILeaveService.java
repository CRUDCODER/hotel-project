package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.Leave;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/2/2 9:23
 */
public interface ILeaveService extends IService<Leave> {

    List<Leave> fetchLeave(Leave leave);

    /***客户办理离店手续***/

    boolean guestLeave(Leave leave);
}

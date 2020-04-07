package com.liujin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujin.entity.Contract;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/2/1 17:45
 */
public interface IContractService extends IService<Contract> {

    List<Contract> fetchContract(Contract contract);

    /**
     * 客户续住接口
     * @return
     */
    boolean guestContract(Contract contract);
}

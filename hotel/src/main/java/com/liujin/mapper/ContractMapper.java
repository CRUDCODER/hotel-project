package com.liujin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujin.entity.Contract;

import java.util.List;

/**
 * @author liujin
 * @date created in 2020/2/1 17:45
 */
public interface ContractMapper extends BaseMapper<Contract> {

    List<Contract> fetchContract(Contract contract);

}

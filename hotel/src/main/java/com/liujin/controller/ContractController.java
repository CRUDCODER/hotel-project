package com.liujin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Contract;
import com.liujin.entity.RespBean;
import com.liujin.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/2/1 17:44
 */
@RestController
public class ContractController {
    @Autowired
    private IContractService contractService;
    @GetMapping("/contracts")
    public Map<String,Object> fetchContract(@RequestParam("page") Integer page,
                                            @RequestParam("limit") Integer limit,
                                            Contract contract){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",contractService.fetchContract(contract));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    @PostMapping("/guestContract")
    public RespBean guestContract(@RequestBody Contract contract)  {
        if (contractService.guestContract(contract)){
            return  RespBean.ok("续住成功",true);
        }
        return RespBean.error("续住失败",false);
    }
}

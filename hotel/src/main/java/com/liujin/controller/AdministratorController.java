package com.liujin.controller;

import com.liujin.entity.Administrator;
import com.liujin.entity.RespBean;
import com.liujin.service.IAdministratorService;
import com.liujin.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liujin
 * @date created in 2020/1/28 13:44
 */
@RestController
public class AdministratorController {

    @Autowired
    private IAdministratorService administratorService;
    @Autowired
    private IRoomService roomService;
    /**
     * 登录成功之后需要做以下操作
     * 查询预定记录 首先查询明天是否有房间已经被预定  如果有则将房间状态改为已预定
     * 也可以将此步骤改为定时器  每天12点定时更新查询
     * 只查询明天是否已经被预定  不查询后天及以后的预定记录
     * @return
     */
    @PostMapping("/login")
    public RespBean userLogin(@RequestBody Administrator administrator){
        System.out.println(administrator);
        if (administratorService.login(administrator)>0){
            roomService.modifyRoomFlag();

            return RespBean.ok("登陆成功",true);
        }
        return RespBean.error("登陆失败",false);
    }
}

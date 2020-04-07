package com.liujin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Member;
import com.liujin.entity.RespBean;
import com.liujin.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/1/30 12:00
 */
@RestController
public class MemberController {
    @Autowired
    private IMemberService memberService;
    @GetMapping("/members")
    public Map<String,Object> fetchMember(@RequestParam("page") Integer page,
                                          @RequestParam("limit") Integer limit,
                                          Member member){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",memberService.fetchMember(member));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    @GetMapping("/member/{memberId}")
    public Member fetchMemberByMemberId(@PathVariable("memberId") Integer memberId){
        return memberService.getById(memberId);
    }

    /**
     * 注册会员账号
     * @return
     */
    @PostMapping("/member")
    public RespBean registeredMember(@RequestBody Member member){
        Integer registeredMember = memberService.registeredMember(member);
        if (registeredMember!=null){
            return RespBean.ok("添加成功",registeredMember);
        }
        return RespBean.error("添加失败",false);
    }
    @PostMapping("/loginMember")
    public RespBean loginMember(@RequestBody Member member){
        Member member1 = memberService.fetchMemberByNumberAndPassword(member);
        if (member1!=null){
            return RespBean.ok("登录成功",member1);
        }
        return RespBean.error("登录失败",false);
    }
    @GetMapping("/fetchMemberForSelect")
    public List<Member> fetchMemberForSelect(){
        return memberService.getBaseMapper().selectList(null);
    }
}

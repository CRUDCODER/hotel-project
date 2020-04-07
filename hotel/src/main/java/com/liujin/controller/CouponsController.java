package com.liujin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.Coupons;
import com.liujin.entity.RespBean;
import com.liujin.service.ICouponsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/2/1 9:44
 */
@RestController
public class CouponsController {
    @Autowired
    private ICouponsService couponsService;

    /**
     * 获取所有优惠卷信息
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/coupons")
    public Map<String,Object> fetchCoupons(@RequestParam("page") Integer page,
                                           @RequestParam("limit") Integer limit){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",couponsService.getBaseMapper().selectList(null));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }

    /**
     * 新增一个优惠卷信息
     * @param coupons
     * @return
     */
    @PostMapping("/coupons")
    public RespBean  increaseCoupons(@RequestBody Coupons coupons){
        coupons.setCouponsFlag(1);
        if (coupons.insert()){
            return RespBean.ok("添加成功",true);
        }
         return RespBean.error("添加失败",false);
    }

    /**
     * 检查优惠卷名称是否重复
     * @param couponsName
     * @return
     */
    @GetMapping("/checkCouponsName/{couponsName}")
    public Integer checkCouponsName(@PathVariable("couponsName") String couponsName){
        return couponsService.getBaseMapper().selectCount
                (new QueryWrapper<Coupons>().eq("coupons_name",couponsName));
    }

    /**
     * 根据优惠卷编号查询优惠卷信息
     * @param couponsId
     * @return
     */
    @GetMapping("/coupons/{couponsId}")
    public Coupons fetchCouponsByCouponsId(@PathVariable("couponsId") Integer couponsId){
        return couponsService.getById(couponsId);
    }
    @GetMapping("/couponsByCouponsName/{couponsName}")
    public Coupons fetchCouponsByCouponsName(@PathVariable("couponsName") String couponsName){
        return couponsService.getOne(new QueryWrapper<Coupons>().eq("coupons_name",couponsName));
    }

    /**
     * 修改优惠卷信息
     * @return
     */
    @PostMapping("/modifyCoupons")
    public RespBean modifyCoupons(@RequestBody Coupons coupons){
        if (coupons.updateById()){
            return RespBean.ok("修改成功",true);
        }
        return RespBean.error("修改失败",false);
    }

    /**
     * 下架优惠卷 使优惠卷不可见
     * @param couponsId
     * @return
     */
    @DeleteMapping("/coupons/{couponsId}")
    public RespBean removeCoupons(@PathVariable("couponsId") Integer couponsId){
        boolean update = couponsService.update
                (new UpdateWrapper<Coupons>()
                        .set("coupons_flag", 0)
                        .eq("coupons_id", couponsId));
        if (update){
            return RespBean.ok("下架成功",true);
        }
        return RespBean.error("下架失败");
    }

    @DeleteMapping("/shelvesCoupons/{couponsId}")
    public RespBean shelvesCoupons(@PathVariable("couponsId") Integer couponsId){
        boolean update = couponsService.update
                (new UpdateWrapper<Coupons>()
                        .set("coupons_flag", 1)
                        .eq("coupons_id", couponsId));
        if (update){
            return RespBean.ok("上架成功",true);
        }
        return RespBean.error("上架失败");
    }


















}

package com.liujin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liujin.entity.RespBean;
import com.liujin.entity.RoomCategory;
import com.liujin.service.IRoomCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujin
 * @date created in 2020/1/28 13:24
 */
@RestController
public class RoomCategoryController {
    @Autowired
    private IRoomCategoryService roomCategoryService;

    @GetMapping("/roomCategorys")
    public Map<String,Object> fetchAllRoomCategoryInfo(@RequestParam("page") Integer page,
                                                       @RequestParam("limit") Integer limit){
        Map<String,Object> map=new HashMap<>(16);
        Page<Object> page1 = PageHelper.startPage(page, limit);
        map.put("data",roomCategoryService.getBaseMapper().selectList(null));
        map.put("count",page1.getTotal());
        map.put("code",0);
        return map;
    }
    @PostMapping("/roomCategory")
    @Transactional(rollbackFor = Exception.class)
    public RespBean increaseRoomCategory(@RequestBody RoomCategory roomCategory){
        if (roomCategory.insert()){
            return RespBean.ok("新增成功",true);
        }
        return RespBean.error("新增失败",false);
    }
    @PostMapping("/modifyRoomCategory")
    @Transactional(rollbackFor = Exception.class)
    public RespBean modifyRoomCategory(@RequestBody RoomCategory roomCategory){
        if (roomCategory.updateById()){
            return RespBean.ok("修改成功",true);
        }
        return RespBean.error("修改失败",false);
    }
    @DeleteMapping("/roomCategory/{roomCategoryId}")
    @Transactional(rollbackFor = Exception.class)
    public RespBean removeRoomCategory(@PathVariable("roomCategoryId") Integer roomCategoryId){
        if (roomCategoryService.removeById(roomCategoryId)){
            return RespBean.ok("删除成功",true);
        }
        return RespBean.error("删除失败",false);
    }
    @GetMapping("/roomCategory/{roomCategoryId}")
    public RoomCategory getRoomCategoryByRoomCategoryId(@PathVariable("roomCategoryId") Integer roomCategoryId){
        return roomCategoryService.getById(roomCategoryId);
    }

    @GetMapping("/checkRoomCategoryName/{roomCategoryName}")
    public RespBean checkRoomCategoryName(@PathVariable("roomCategoryName") String roomCategoryName){
        if (roomCategoryService.getBaseMapper().selectCount(new QueryWrapper<RoomCategory>().eq("room_category_name",roomCategoryName))>0){
            return RespBean.ok("房间类别名称已存在",false);
        }
        return RespBean.error("可以新增的房间类别名称",true);
    }
    @GetMapping("/RoomCategoryForSelect")
    public List<RoomCategory> fetchRoomCategoryForSelect(){
        return roomCategoryService.getBaseMapper().selectList(null);
    }
}

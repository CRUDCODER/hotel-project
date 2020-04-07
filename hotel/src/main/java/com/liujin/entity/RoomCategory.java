package com.liujin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author liujin
 * @date created in 2020/1/28 13:20
 * 房间类别
 */
@TableName(value = "room_category")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Builder
public class RoomCategory extends Model<RoomCategory> {
    @TableId(type = IdType.AUTO,value = "room_category_id")
    private  Integer roomCategoryId;
    @TableField(value = "room_category_name")
    private String roomCategoryName;
    @TableField(value = "room_category_remark")
    private String roomCategoryRemark;
}

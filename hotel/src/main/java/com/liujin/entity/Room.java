package com.liujin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author liujin
 * @date created in 2020/1/29 11:54
 */
@TableName(value = "room")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Room extends Model<Room> {
    @TableId(type = IdType.AUTO,value = "room_id")
    private Integer roomId;
    @TableField(value = "room_number")
    private Integer roomNumber;
    @TableField(value = "room_category_id")
    private Integer roomCategoryId;
    @TableField(value = "room_area")
    private String roomArea;
    @TableField(value = "room_floor")
    private Integer roomFloor;
    @TableField(value = "room_money")
    private float roomMoney;
    @TableField(value = "room_img")
    private String roomImg;
    @TableField(value = "room_flag")
    private Integer roomFlag;

    @TableField(exist = false)
    private String categoryName;
    @TableField(exist = false)
    private float price2;
}

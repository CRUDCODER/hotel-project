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
 * @date created in 2020/2/2 12:58
 */
@TableName(value = "special")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Special  extends Model<Special> {
    @TableId(type = IdType.AUTO,value = "special_id")
    private Integer specialId;
    @TableField(value = "room_id")
    private Integer roomId;
    @TableField(value = "special_money")
    private float specialMoney;
    @TableField(value = "special_remark")
    private String specialRemark;
    @TableField(value = "is_show")
    private Integer isShow;
    @TableField(value = "special_date")
    private String specialDate;
    @TableField(exist = false)
    private String roomNumber;

}

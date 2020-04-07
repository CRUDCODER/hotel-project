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
 * @date created in 2020/2/2 11:29
 */
@TableName(value = "checkout")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Checkout extends Model<Checkout> {
    @TableId(type = IdType.AUTO,value = "checkout_id")
    private Integer checkoutId;
    @TableField(value = "checkout_date")
    private String checkoutDate;
    @TableField(value = "checkout_guest")
    private String checkoutGuest;
    @TableField(value = "checkout_phone")
    private String checkoutPhone;
    @TableField(value = "checkout_money")
    private float checkoutMoney;
}

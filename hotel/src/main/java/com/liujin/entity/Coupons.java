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
 * @date created in 2020/2/1 9:41
 */
@TableName(value = "coupons")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Coupons extends Model<Coupons> {
    @TableId(type = IdType.AUTO,value = "coupons_id")
    private Integer couponsId;
    @TableField(value = "coupons_name")
    private String couponsName;
    @TableField(value = "coupons_money")
    private float couponMoney;
    @TableField(value = "coupons_remark")
    private String couponsRemark;
    @TableField(value = "need_score")
    private Integer needScore;
    @TableField(value ="coupons_flag")
    private Integer couponsFlag;
}

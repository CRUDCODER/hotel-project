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
 * @date created in 2020/2/2 11:23
 */
@TableName(value = "payfee")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PayFee extends Model<PayFee> {

    @TableId(type = IdType.AUTO,value = "payfee_id")
    private Integer payFeeId;
    @TableField(value = "payfee_category")
    private String payFeeCategory;
    @TableField(value = "payfee_date")
    private String payFeeDate;
    @TableField(value = "payfee_money")
    private float payFeeMoney;
    @TableField(value = "payfee_name")
    private String payFeeName;
    @TableField(value = "payfee_phone")
    private String payFeePhone;
}

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
 * @date created in 2020/1/30 12:34
 */
@TableName(value = "member_deposit")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MemberDeposit extends Model<MemberDeposit> {

    @TableId(type = IdType.AUTO,value = "deposit_id")
    private Integer depositId;
    @TableField(value = "member_id")
    private Integer memberId;
    @TableField(value = "deposit_money")
    private float depositMoney;
    @TableField(value = "deposit_date")
    private String depositDate;
    @TableField(exist = false)
    private Integer memberNumber;
}

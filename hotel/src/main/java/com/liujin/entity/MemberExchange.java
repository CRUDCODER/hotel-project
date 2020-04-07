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
 * @date created in 2020/2/1 9:50
 */
@TableName(value = "member_exchange")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MemberExchange extends Model<MemberExchange> {
    @TableId(type = IdType.AUTO,value = "exchange_id")
    private Integer exchangeId;
    @TableField(value = "member_id")
    private Integer memberId;
    @TableField(value = "coupons_id")
    private Integer couponsId;
    @TableField(value = "exchange_date")
    private String exchangeDate;
    @TableField(value = "consume_score")
    private Integer consumeScore;
    @TableField(value = "exchange_flag")
    private Integer exchangeFlag;

    @TableField(exist = false)
    private Integer memberNumber;
    @TableField(exist = false)
    private String memberName;
    @TableField(exist = false)
    private String couponsName;
}

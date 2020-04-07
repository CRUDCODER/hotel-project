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
 * @date created in 2020/2/1 9:46
 */
@TableName(value = "member_consume")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MemberConsume extends Model<MemberConsume> {
    @TableId(type = IdType.AUTO,value = "consume_id")
    private Integer consumeId;
    @TableField(value = "member_id")
    private Integer memberId;
    @TableField(value = "consume_category")
    private String consumeCategory;
    @TableField(value = "consume_money")
    private float consumeMoney;
    @TableField(value = "consume_date")
    private String consumeDate;
    @TableField(exist = false)
    private Integer memberNumber;
    @TableField(exist = false)
    private String memberName;
}

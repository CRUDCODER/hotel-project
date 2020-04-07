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
 * @date created in 2020/1/30 11:45
 */
@TableName(value = "member")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Member extends Model<Member> {
    @TableId(type = IdType.AUTO,value = "member_id")
    private Integer memberId;
    @TableField(value = "member_number")
    private Integer memberNumber;
    @TableField(value = "member_phone")
    private String memberPhone;
    @TableField(value = "member_password")
    private String memberPassword;
    @TableField(value = "member_money")
    private float memberMoney;
    @TableField(value = "member_score")
    private Integer memberScore;
    @TableField(value = "member_card")
    private String memberCard;
    @TableField(value = "member_mail")
    private String memberMail;
    @TableField(value = "member_name")
    private String memberName;
    @TableField(value = "member_img")
    private String memberImg;

    /**
     * 用于查询  不用于显示
     */
    @TableField(exist = false)
    private float money2;
}

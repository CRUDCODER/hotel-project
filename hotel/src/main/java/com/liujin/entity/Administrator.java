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
 * @date created in 2020/1/28 13:42
 */
@TableName(value = "administrator")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Builder
public class Administrator extends Model<Administrator> {
    @TableId(type = IdType.AUTO,value = "user_id")
    private Integer userId;
    @TableField(value = "user_account")
    private String userAccount;
    @TableField(value = "user_password")
    private String userPassword;
    @TableField(value = "user_name")
    private String userName;
}

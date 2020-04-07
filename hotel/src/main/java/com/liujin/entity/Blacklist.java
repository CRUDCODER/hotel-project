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
 * @date created in 2020/2/2 13:01
 */
@TableName(value = "blacklist")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Blacklist extends Model<Blacklist> {
    @TableId(type = IdType.AUTO,value = "blacklist_id")
    private Integer blacklistId;
    @TableField(value = "blacklist_name")
    private String blacklistName;
    @TableField(value = "blacklist_card")
    private String blacklistCard;
    @TableField(value = "blacklist_date")
    private String blacklistDate;
}

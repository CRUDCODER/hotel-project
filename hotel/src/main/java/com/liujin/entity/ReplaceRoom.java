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
 * @date created in 2020/2/1 13:22
 */
@TableName(value = "replaces")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ReplaceRoom extends Model<ReplaceRoom> {
    @TableId(type = IdType.AUTO,value = "replace_id")
    private Integer replaceId;
    @TableField(value = "live_id")
    private Integer liveId;
    @TableField(value = "replace_date")
    private String replaceDate;
    @TableField(value = "room_id")
    private Integer roomId;
    @TableField(value = "replace_remark")
    private String replaceRemark;
    @TableField(value = "replace_money")
    private float replaceMoney;

    @TableField(exist = false)
    private String guestName;
    @TableField(exist = false)
    private String guestPhone;
    @TableField(exist = false)
    private String guestCard;
    @TableField(exist = false)
    private String roomNumber;
}

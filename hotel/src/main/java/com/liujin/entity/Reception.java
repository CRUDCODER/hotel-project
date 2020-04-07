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
 * @date created in 2020/1/31 10:22
 */
@TableName(value = "reception")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Reception extends Model<Reception> {

    @TableId(type = IdType.AUTO,value = "reception_id")
    private Integer receptionId;
    @TableField(value = "guest_name")
    private String guestName;
    @TableField(value = "guest_phone")
    private String guestPhone;
    @TableField(value = "guest_card")
    private String guestCard;
    @TableField(value = "guest_flag")
    private Integer guestFlag;
    @TableField(value = "reception_remark")
    private String receptionRemark;
    @TableField(value = "reservation_id")
    private Integer reservationId;
    @TableField(value = "reception_date")
    private String receptionDate;
    @TableField(value = "room_id")
    private Integer roomId;
    @TableField(exist = false)
    private String roomNumber;
}

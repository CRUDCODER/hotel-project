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
 * @date created in 2020/1/30 13:32
 */
@TableName(value = "room_reservation")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RoomReservation extends Model<RoomReservation> {
    @TableId(type = IdType.AUTO,value = "reservation_id")
    private Integer reservationId;
    @TableField(value = "member_id")
    private Integer memberId;
    @TableField(value = "room_id")
    private Integer roomId;
    @TableField(value = "pay_money")
    private float payMoney;
    @TableField(value = "pay_date")
    private String payDate;
    @TableField(value = "reservation_date")
    private String reservationDate;
    @TableField(value = "reservation_flag")
    private Integer reservationFlag;
    @TableField(value = "reservation_remark")
    private String reservationRemark;
    @TableField(value = "reservation_number")
    private Integer reservationNumber;
    @TableField(value = "exchange_id")
    private Integer exchangeId;
    @TableField(exist = false)
    private String memberNumber;
    @TableField(exist = false)
    private String memberName;
    @TableField(exist = false)
    private String memberPhone;
    @TableField(exist = false)
    private String roomNumber;
    @TableField(exist = false)
    private String couponsName;



    /*********统计报表所需字段************/
    @TableField(exist = false)
    private String name;
    @TableField(exist = false)
    private String value;
}

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
 * @date created in 2020/2/1 13:23
 */
@TableName(value = "leave")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Leave  extends Model<Leave> {
    @TableId(value = "leave_id",type = IdType.AUTO)
    private Integer leaveId;
    @TableField(value = "live_id")
    private Integer liveId;
    @TableField(value = "leave_date")
    private String leaveDate;
    @TableField(value = "refund_money")
    private float refundMoney;
    @TableField(value = "leave_remark")
    private String leaveRemark;



    @TableField(exist = false)
    private String guestName;
    @TableField(exist = false)
    private String guestPhone;
    @TableField(exist = false)
    private String guestCard;
    @TableField(exist = false)
    private String roomNumber;
}

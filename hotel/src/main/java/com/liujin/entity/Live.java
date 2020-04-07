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
 * @date created in 2020/1/31 10:25
 */
@TableName(value = "live")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Live extends Model<Live> {
    @TableId(type = IdType.AUTO,value = "live_id")
    private Integer liveId;
    @TableField(value = "reception_id")
    private Integer receptionId;
    @TableField(value = "live_date")
    private String liveDate;
    @TableField(value = "live_remark")
    private String liveRemark;
    @TableField(value = "live_money")
    private float liveMoney;
    @TableField(value = "end_date")
    private String endDate;
    @TableField(value = "live_flag")
    private Integer liveFlag;
    @TableField(exist = false)
    private Integer roomNumber;

    @TableField(exist = false)
    private String guestName;
    @TableField(exist = false)
    private String guestPhone;
    @TableField(exist = false)
    private String guestCard;
    @TableField(exist = false)
    private Integer guestFlag;
}

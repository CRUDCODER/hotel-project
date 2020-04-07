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
 * 续住
 */
@TableName(value = "contract")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Contract extends Model<Contract> {
    @TableId(type = IdType.AUTO,value = "contract_id")
    private Integer contractId;
    @TableField(value = "live_id")
    private Integer liveId;
    @TableField(value = "contract_date")
    private String contractDate;
    @TableField(value = "contract_remark")
    private String contractRemark;
    @TableField(value = "contract_money")
    private float contractMoney;

    @TableField(exist = false)
    private String guestName;
    @TableField(exist = false)
    private String guestPhone;
    @TableField(exist = false)
    private String guestCard;
    @TableField(exist = false)
    private String roomNumber;
}

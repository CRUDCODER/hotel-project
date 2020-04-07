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
 * @date created in 2020/1/30 10:18
 */
@TableName(value = "goods_outbound")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GoodsOutbound extends Model<GoodsOutbound> {
    @TableId(type = IdType.AUTO,value = "outbound_id")
    private Integer outboundId;
    @TableField(value = "room_goods_id")
    private Integer roomGoodsId;
    @TableField(value = "outbound_date")
    private String outboundDate;
    @TableField(value = "outbound_count")
    private Integer outboundCount;
    @TableField(exist = false)
    private String roomNumber;
    @TableField(exist = false)
    private String goodsName;
    @TableField(exist = false)
    private Integer goodsCount;
    @TableField(exist = false)
    private Integer goodsId;
}

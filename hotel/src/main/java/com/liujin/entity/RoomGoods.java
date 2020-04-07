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
 * @date created in 2020/1/29 19:14
 */
@TableName(value = "room_goods")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)

public class RoomGoods extends Model<RoomGoods> {

    @TableId(type = IdType.AUTO,value = "room_goods_id")
    private Integer roomGoodsId;
    @TableField(value = "goods_id")
    private  Integer goodsId;
    @TableField(value = "room_id")
    private Integer roomId;
    @TableField(value = "goods_count")
    private Integer goodsCount;
    @TableField(value = "date_updated")
    private String dateUpdated;
    @TableField(exist = false)
    private Integer roomNumber;
    @TableField(exist = false)
    private String goodsName;

}

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
 * @date created in 2020/1/29 18:33
 */
@TableName(value = "goods")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Goods  extends Model<Goods> {
    @TableId(type = IdType.AUTO,value = "goods_id")
    private Integer goodsId;
    @TableField(value = "goods_name")
    private String goodsName;
    @TableField(value = "goods_category_id")
    private Integer goodsCategoryId;
    @TableField(value = "goods_price")
    private float goodsPrice;
    @TableField(value = "goods_img")
    private String goodsImg;
    @TableField(value = "goods_count")
    private Integer goodsCount;

    @TableField(exist = false)
    private String goodsCategoryName;
    @TableField(exist = false)
    private Integer count2;
}

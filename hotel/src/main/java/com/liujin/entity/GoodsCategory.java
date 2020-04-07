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
 * @date created in 2020/1/29 14:37
 */
@TableName(value = "goods_category")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GoodsCategory  extends Model<GoodsCategory> {

    @TableId(type = IdType.AUTO,value = "goods_category_id")
    private Integer goodsCategoryId;
    @TableField(value = "goods_category_name")
    private String goodsCategoryName;
    @TableField(value = "goods_category_remark")
    private String goodsCategoryRemark;

}

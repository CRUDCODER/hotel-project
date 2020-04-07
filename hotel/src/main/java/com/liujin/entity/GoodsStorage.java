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
 * @date created in 2020/1/30 9:24
 */
@TableName(value = "goods_storage")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GoodsStorage extends Model<GoodsStorage> {

    @TableId(type = IdType.AUTO,value = "storage_id")
    private Integer storageId;
    @TableField(value = "goods_id")
    private Integer goodsId;
    @TableField(value = "storage_date")
    private String storageDate;
    @TableField(value = "storage_count")
    private Integer storageCount;

    /**
     * 出库经办人  暂时保留
     */
    @TableField(value = "storage_admin")
    private Integer storageAdmin;
    @TableField(exist = false)
    private String goodsName;
}

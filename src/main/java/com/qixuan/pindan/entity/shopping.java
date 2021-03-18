package com.qixuan.pindan.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.beans.Transient;

/*
拼单
 */
@Data
@TableName("shopping_tb")
public class shopping {

    @TableId("id")
    private String id;
    private int order_id;
    private String product_type;
    private int price_cad;

    @TableField(exist = false)
    private order order;

    public shopping(String id, int order_id, String product_type, int price_cad, com.qixuan.pindan.entity.order order) {
        this.id = id;
        this.order_id = order_id;
        this.product_type = product_type;
        this.price_cad = price_cad;
        this.order = order;
    }
}

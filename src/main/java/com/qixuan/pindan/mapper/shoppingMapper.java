package com.qixuan.pindan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qixuan.pindan.entity.order;
import com.qixuan.pindan.entity.shopping;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

@Mapper
public interface shoppingMapper extends BaseMapper<shopping> {
    @Select("select * from shopping_tb ")
    @Results({
            @Result(id = true ,column = "id" , property = "id"),
            @Result(column = "price_cad" , property = "price_cad"),
            @Result(column = "product_type", property = "product_type"),
            @Result(column = "order_id",property = "order_id"),
            @Result(property = "order",
                    column="order_id",
                    javaType = order.class,
                    one = @One(select = "com.qixuan.pindan.mapper.orderMapper.selectOrder",
                            fetchType = FetchType.EAGER))})
//            ", order_tb where shopping_tb.identify_id = order_tb.order_id")
    List<shopping> selectShop();


}

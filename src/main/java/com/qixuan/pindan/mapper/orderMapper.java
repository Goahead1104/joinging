package com.qixuan.pindan.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qixuan.pindan.entity.order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface orderMapper extends BaseMapper<order> {
    @Select("select * from order_tb where order_id = #{order_id}")
    order selectOrder(int order_id);


    @Options(useGeneratedKeys = true, keyProperty = "order_id")
    @Insert("INSERT INTO order_tb(order_id,"+
            "type," +
            "publish_date," +
            "publish_time," +
            "publisher," +
            "image_route," +
            "status," +
            "content) " +
            "VALUES(#{order_id},#{type}," +
            "#{publish_date},#{publish_time}," +
            "#{publisher},#{image_route}," +
            "#{status},#{content})")
   int insertOrder(order order);
}

package com.qixuan.pindan.service;

import com.qixuan.pindan.entity.shopping;
import com.qixuan.pindan.mapper.orderMapper;
import com.qixuan.pindan.mapper.shoppingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingService {
    @Autowired
    private shoppingMapper shoppingmapper;
    @Autowired
    private orderMapper ordermapper;

    /*
    查询拼单
     */
    public List<shopping> selectShop(){
        return shoppingmapper.selectShop();
    }

    /*
    插入拼单购物
     */
    @Transactional
    public int insertShop(shopping shop){
        ordermapper.insertOrder(shop.getOrder());
        System.out.println(shop.getOrder().getOrder_id()+"=======");
        shop.setOrder_id(shop.getOrder().getOrder_id());
        return shoppingmapper.insert(shop);
    }

    /*
    删除拼单购物信息
     */
    public int deleteShop(shopping shop){
        shoppingmapper.deleteById(shop.getId());
        return ordermapper.deleteById(shop.getOrder().getOrder_id());
    }

    /*
    修改拼单购物信息
     */
//    public int update(shopping shop){
//        //return shoppingmapper.update()
//    }
}

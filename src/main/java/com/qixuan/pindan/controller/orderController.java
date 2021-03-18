package com.qixuan.pindan.controller;


import com.qixuan.pindan.entity.ResultMsg.CodeMsg;
import com.qixuan.pindan.entity.ResultMsg.Result;
import com.qixuan.pindan.entity.car;
import com.qixuan.pindan.entity.house;
import com.qixuan.pindan.entity.order;
import com.qixuan.pindan.entity.shopping;
import com.qixuan.pindan.service.CarService;
import com.qixuan.pindan.service.HouseService;
import com.qixuan.pindan.service.ShoppingService;
import com.qixuan.pindan.service.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class orderController {

    @Autowired
    private ShoppingService shoppingservice;

    @Autowired
    private HouseService houseService;

    @Autowired
    private CarService carService;

    @Autowired
    private orderService orderservice;


    /*
    获取购物拼单的信息
     */
    @RequestMapping("/getShop")
    public List<shopping> selectShop(HttpSession session){
//        if(session.getId() == null) {
//            return null;
//        }
        return shoppingservice.selectShop();
    }

    /*
    发布拼单购物信息
     */
    @RequestMapping(value = "/publishShop",method = RequestMethod.POST)
    public Result publicShop(String id,
                             String product_type,
                             int price_cad,
                             int order_id,
                             String type,
                             String publish_date,
                             String publish_time,
                             int publisher,
                             String image_route,
                             String status,
                             String content,
                             MultipartFile image){

        //新订单
        order neworder = new order(
         order_id,
         type,
         publish_date,
         publish_time,
         publisher,
         image_route,
         status,
         content
        );

        //订单内详
        shopping newshop = new shopping(
         id,
         order_id,
         product_type,
         price_cad,
         neworder);

        //图片上传
        if(image != null) {
            newshop.getOrder().setImage_route(orderservice.upload_image(image));
        }

        //处理信息字段
        //购物订单号自定义生成
        /*
        自己写
         */
        newshop.setId("自定义");

        //时间设定
        SimpleDateFormat dt = new SimpleDateFormat(
                "yyyy-MM-dd");
        String date = dt.format(new Date());
        SimpleDateFormat dd = new SimpleDateFormat(
                "HH:mm:ss");
        String time = dd.format(new Date());

        newshop.getOrder().setPublish_date(date);
        newshop.getOrder().setPublish_time(time);

        //状态审核
        newshop.getOrder().setStatus("3");

        try {
            //插入数据库
            shoppingservice.insertShop(newshop);
        }catch(Exception e){
            return Result.error(CodeMsg.SERVER_ERROR);
        }

        return Result.success("发布成功！");
    }

    /*
    获取拼房的信息
   */
    @RequestMapping("/getHouse")
    public List<house> selectHouse(HttpSession session){
//        if(session.getId() == null) {
//            return null;
//        }
        return houseService.selectHouse();
    }

    /*
    获取拼车的信息
    */
    @RequestMapping("/getCar")
    public List<car> selectCar(HttpSession session){
        if(session.getId() == null){
            return null;
//            car Msg = new car();
//            Msg.setContent("请登录！");
//            List<car> car = new LinkedList<car>();
//            car.add(Msg);
//            return car;
        };
        return carService.selectCar();
    }

//    @RequestMapping("/ifLogin")
//    public Result ifLogin(HttpSession session){
//        if(session.getId() == null){
//            //return Result.error();
//            return
//        }
//        return Result.success(CodeMsg.SUCCESS);
//    }


}


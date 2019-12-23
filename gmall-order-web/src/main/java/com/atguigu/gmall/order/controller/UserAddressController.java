package com.atguigu.gmall.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserAddressController {

    @Reference
    private UserAddressService userAddressService;

    //根据userId查询用户的收货地址的方法
    @RequestMapping("trade")
    @ResponseBody  //作用 ：把后台对象响应转化为json数据  传输到前台
    public List<UserAddress> trade( String userId){
         return    userAddressService.getUserAddressById(userId);

    }
}

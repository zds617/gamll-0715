package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.UserAddress;

import java.util.List;

public interface UserAddressService {

    ////根据user信息查询用户的收货地址的方法
    List<UserAddress> getUserAddressById(String userId);

    ////根据user信息查询用户的收货地址的方法
    List<UserAddress> getUserAddressById(UserAddress userAddress);
}

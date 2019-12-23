package com.atguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.*;
import com.atguigu.gmall.service.ManageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//跨域
@CrossOrigin
public class ManageController {

    //dubbo的消费者注解
    @Reference
    private ManageService manageService;

    @RequestMapping("getBaseCatalog1Info")
    @ResponseBody
    public List<BaseCatalog1> getBaseCatalog1Info() {
        List<BaseCatalog1> catalog1List = manageService.getCatalog1();
        return catalog1List;

    }

    @RequestMapping("getBaseCatalog2Info")
    @ResponseBody //作用就是把后台对象数据响应个为前台json数据
    public List<BaseCatalog2> getBaseCatalog2Info(String catalog1Id) {
        List<BaseCatalog2> catalog2List = manageService.getCatalog2(catalog1Id);
        return catalog2List;

    }

    @RequestMapping("getBaseCatalog3Info")
    @ResponseBody //作用就是把后台对象数据响应个为前台json数据
    public List<BaseCatalog3> getBaseCatalog3Info(String catalog2Id) {
        List<BaseCatalog3> catalog3List = manageService.getCatalog3(catalog2Id);
        return catalog3List;

    }

    @RequestMapping("attrInfoList")
    @ResponseBody //作用就是把后台对象数据响应个为前台json数据
    public List<BaseAttrInfo> attrInfoList(String catalog3Id) {
        List<BaseAttrInfo> attrList = manageService.getAttrList(catalog3Id);
        return attrList;

    }

    //保存平台属性和属性值  参数是一个对象
    @RequestMapping("saveAttrInfo")
    public  void  saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){

                  manageService.saveAttrInfo(baseAttrInfo);

    }
    //修改平台属性和属性值  参数是一个对象
//    @RequestMapping("getAttrValueList")
//    public List<BaseAttrValue>  getAttrValueList(String attrId){
//      return    manageService.getAttrValueList(attrId);
//
//    }
    //先查询回显平台属性的值
    @RequestMapping("getAttrValueList")
    public List<BaseAttrValue>  getAttrValueList(String attrId){
               BaseAttrInfo baseAttrInfo=    manageService.getAttrInfo(attrId);
               return  baseAttrInfo.getAttrValueList();

    }

}

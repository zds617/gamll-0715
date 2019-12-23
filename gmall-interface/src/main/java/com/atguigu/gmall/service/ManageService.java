package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.*;

import java.util.List;

public interface ManageService {
    //查询一级分类
    public List<BaseCatalog1> getCatalog1();
    //查询二级分类
    public List<BaseCatalog2> getCatalog2(String catalog1Id);
    //查询三级分类
    public List<BaseCatalog3> getCatalog3(String catalog2Id);
    //查询平台属性信息
    public List<BaseAttrInfo> getAttrList(String catalog3Id);


    //保存平台属性和属性值  参数是一个对象
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);
    //先查询回显平台属性的值
    List<BaseAttrValue> getAttrValueList(String attrId);


    BaseAttrInfo getAttrInfo(String attrId);

    //根据商品属性查询商品信息
    List<SpuInfo> getSpuList(SpuInfo spuInfo);
}

package com.atguigu.gmall.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
public class SpuInfo implements Serializable{

    @Column
    @Id
    //id自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String spuName;

    @Column
    private String description;

    @Column
    private  String catalog3Id;

    //注解表示这个字段属性不插入数据库  是个逻辑字段
    @Transient
    private List<SpuSaleAttr> spuSaleAttrList;

    @Transient
    private List<SpuImage> spuImageList;
}

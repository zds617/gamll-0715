package com.atguigu.gmall.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
public class BaseAttrInfo implements Serializable {

    @Id
    @Column
    //设置其id自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String attrName;
    @Column
    private String catalog3Id;

    //把baseAttrValues封装再集合里作为BaseAttrInfo属性

    //注解表示这个字段属性不插入数据库  是个逻辑字段
    @Transient
    private List <BaseAttrValue> attrValueList;
}

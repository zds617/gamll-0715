package com.atguigu.gmall.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

@Data
public class BaseAttrValue implements Serializable {

    @Id
    @Column
    private String id;
    @Column
    private String valueName;
    @Column
    private String attrId;

    //逻辑字段  不加入数据库
    @Transient
    private String urlParam;
}

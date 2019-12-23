package com.atguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.SpuInfo;
import com.atguigu.gmall.service.ManageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//跨域
@CrossOrigin
public class SpuManageController {
    @Reference
    private ManageService manageService;

    @RequestMapping("spuList")
    @ResponseBody
    public List<SpuInfo> getSpuInfoList(String catalog3Id){
        SpuInfo spuInfo = new SpuInfo();
        spuInfo.setCatalog3Id(catalog3Id);

        List<SpuInfo> spuInfoList = manageService.getSpuList(spuInfo);
        return  spuInfoList;
    }
}

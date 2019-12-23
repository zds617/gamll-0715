package com.atguigu.gmall.manageservice.serivce.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.*;
import com.atguigu.gmall.manageservice.mapper.*;
import com.atguigu.gmall.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//注意这个使用dubbo  这个是提供者
@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private BaseCatalog1Mapper baseCatalog1Mapper;

    @Autowired
    private  BaseCatalog2Mapper baseCatalog2Mapper;

    @Autowired
    private BaseCatalog3Mapper baseCatalog3Mapper;

    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;

    @Autowired
    private  SpuInfoMapper spuInfoMapper;
    //查询一级分类
    @Override
    public List<BaseCatalog1> getCatalog1() {
        List<BaseCatalog1> baseCatalog1List = baseCatalog1Mapper.selectAll();
        return baseCatalog1List;
    }

    //查询二级分类
    @Override
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {
        BaseCatalog2 baseCatalog2 = new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);
        List<BaseCatalog2> baseCatalog2List = baseCatalog2Mapper.select(baseCatalog2);
        return baseCatalog2List;
    }


    //查询三级分类
    //public List<BaseCatalog3> getCatalog3(BaseCatalog3 baseCatalog3)
    @Override
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {
        BaseCatalog3 baseCatalog3 = new BaseCatalog3();
        baseCatalog3.setCatalog2Id(catalog2Id);
        List<BaseCatalog3> baseCatalog3List = baseCatalog3Mapper.select(baseCatalog3);
        return baseCatalog3List;
    }

    //查询平台属性信息
    @Override
    //public List<BaseAttrInfo> getAttrList(String catalog3Id)
    public List<BaseAttrInfo> getAttrList(String catalog3Id) {
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(catalog3Id);
        List<BaseAttrInfo> baseAttrInfoList = baseAttrInfoMapper.select(baseAttrInfo);
        return baseAttrInfoList;
    }


    /**
     * 保存平台属性信息  包括保存平台属性和其值
     * @param baseAttrInfo
     */
    //保存平台属性和属性值  参数是一个对象
    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        //因为在前台定义的原因  增加和修改都是一个方法  所以修改在这方法里实现

        if(baseAttrInfo.getId()!=null && baseAttrInfo.getId().length()>0){

            baseAttrInfoMapper.updateByPrimaryKey(baseAttrInfo);
        }else {

        //先保存平台属性
         baseAttrInfoMapper.insertSelective(baseAttrInfo);
        }

        //在这里修改和之前不一样  如果有id 进行修改  先删除平台信息 再添加平台信息
        BaseAttrValue baseAttrValue1 = new BaseAttrValue();
        baseAttrValue1.setAttrId(baseAttrInfo.getId());
        //删除平台属性信息
        baseAttrValueMapper.delete(baseAttrValue1);

        //再保存属性值  在这里需要循环遍历一下
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        //取得属性值  得到一个集合 然后遍历
        if(attrValueList!=null && attrValueList.size()>0){
            for (int i = 0; i < attrValueList.size(); i++) {

           //设置属性值的id  这个id在实体类要设置自增注解
                BaseAttrValue baseAttrValue = attrValueList.get(i);
                baseAttrValue.setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insertSelective(baseAttrValue);
            }
        }
    }
    //先查询回显平台属性的值
    @Override
    public List<BaseAttrValue> getAttrValueList(String attrId) {

        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(attrId);
        List<BaseAttrValue> attrValueList = baseAttrValueMapper.select(baseAttrValue);
        return attrValueList;
    }


    @Override
    public BaseAttrInfo getAttrInfo(String attrId) {
        BaseAttrInfo baseAttrInfo = baseAttrInfoMapper.selectByPrimaryKey(attrId);
        baseAttrInfo.setAttrValueList(getAttrValueList(attrId));
        return baseAttrInfo;
    }

    //根据商品属性查询商品信息
    @Override
    public List<SpuInfo> getSpuList(SpuInfo spuInfo) {
        List<SpuInfo> infoList = spuInfoMapper.select(spuInfo);
        return infoList;
    }
}

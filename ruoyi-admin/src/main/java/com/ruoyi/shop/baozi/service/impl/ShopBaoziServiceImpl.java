package com.ruoyi.shop.baozi.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ruoyi.shop.baozi.vo.BaoZiTongJiVo;
import com.ruoyi.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.shop.baozi.mapper.ShopBaoziMapper;
import com.ruoyi.shop.baozi.domain.ShopBaozi;
import com.ruoyi.shop.baozi.service.IShopBaoziService;

/**
 * baoziService业务层处理
 * 
 * @author ruoyi
 * @date 2023-05-20
 */
@Service
public class ShopBaoziServiceImpl implements IShopBaoziService 
{
    private static String NAME = "包子铺";

    @Autowired
    private ShopBaoziMapper shopBaoziMapper;

    /**
     * 查询baozi
     * 
     * @param id baozi主键
     * @return baozi
     */
    @Override
    public ShopBaozi selectShopBaoziById(Long id)
    {
        return shopBaoziMapper.selectShopBaoziById(id);
    }

    /**
     * 查询baozi列表
     * 
     * @param shopBaozi baozi
     * @return baozi
     */
    @Override
    public List<ShopBaozi> selectShopBaoziList(ShopBaozi shopBaozi)
    {
        return shopBaoziMapper.selectShopBaoziList(shopBaozi);
    }

    /**
     * 新增baozi
     * 
     * @param shopBaozi baozi
     * @return 结果
     */
    @Override
    public int insertShopBaozi(ShopBaozi shopBaozi)
    {
        BigDecimal getPayMoney = shopBaozi.getGetPayMoney() == null ? BigDecimal.valueOf(0) : shopBaozi.getGetPayMoney();
        BigDecimal getWechatMoney = shopBaozi.getGetWechatMoney() == null ? BigDecimal.valueOf(0) : shopBaozi.getGetWechatMoney();
        BigDecimal getMoney = shopBaozi.getGetMoney() == null ? BigDecimal.valueOf(0) : shopBaozi.getGetMoney();
        //总收入=微信+支付宝+现金
        BigDecimal allMoney = getPayMoney.add(getWechatMoney).add(getMoney);
        shopBaozi.setName(NAME);
        Date createDate = shopBaozi.getCreateDate();
        //每天的数据只能有一条校验
        ShopBaozi checkDate = new ShopBaozi();
        checkDate.setCreateDate(createDate);
        List<ShopBaozi> checkList = shopBaoziMapper.selectShopBaoziList(checkDate);
        if(checkList.size()>0){
            return 0;
        }
        shopBaozi.setDay(DateUtil.getWeek(createDate));
        //设置总收入（当日营业额）
        shopBaozi.setGetAllMoney(allMoney);
        //设置当日净利润
        BigDecimal putHouseMoney = shopBaozi.getPutHouseMoney() == null ? BigDecimal.valueOf(0) : shopBaozi.getPutHouseMoney();
        BigDecimal putMoney = shopBaozi.getPutMoney() == null ? BigDecimal.valueOf(0) : shopBaozi.getPutMoney();
        //净利润 总收入-房租-进货
        BigDecimal actualMoney = allMoney.subtract(putHouseMoney).subtract(putMoney);
        shopBaozi.setActualMoney(actualMoney);
        //geng_salary 净利润 * 1/3 向下取整
        double a = 1;
        double b = 3;
        double c = a/b;
        BigDecimal gengSalary = actualMoney.divide( BigDecimal.valueOf(3),0,BigDecimal.ROUND_DOWN).setScale(0,BigDecimal.ROUND_DOWN);
        //guo_salary 净利润 * 2/3 向下取整
        double d = 2;
        double e = 3;
        double f = d/e;
        BigDecimal guoSalary = actualMoney.divide( BigDecimal.valueOf(3),0,BigDecimal.ROUND_DOWN).multiply(BigDecimal.valueOf(2)).setScale(0,BigDecimal.ROUND_DOWN);
        shopBaozi.setGengSalary(gengSalary);
        shopBaozi.setGuoSalary(guoSalary);
        return shopBaoziMapper.insertShopBaozi(shopBaozi);
    }

    /**
     * 修改baozi
     * 
     * @param shopBaozi baozi
     * @return 结果
     */
    @Override
    public int updateShopBaozi(ShopBaozi shopBaozi)
    {
        BigDecimal getPayMoney = shopBaozi.getGetPayMoney() == null ? BigDecimal.valueOf(0) : shopBaozi.getGetPayMoney();
        BigDecimal getWechatMoney = shopBaozi.getGetWechatMoney() == null ? BigDecimal.valueOf(0) : shopBaozi.getGetWechatMoney();
        BigDecimal getMoney = shopBaozi.getGetMoney() == null ? BigDecimal.valueOf(0) : shopBaozi.getGetMoney();
        //总收入=微信+支付宝+现金
        BigDecimal allMoney = getPayMoney.add(getWechatMoney).add(getMoney);
        shopBaozi.setName(NAME);
        Date createDate = shopBaozi.getCreateDate();
        //每天的数据只能有一条校验
        ShopBaozi checkDate = new ShopBaozi();
        checkDate.setCreateDate(createDate);
        List<ShopBaozi> checkList = shopBaoziMapper.selectShopBaoziList(checkDate);
        if(checkList.size()>0){
            if(!checkList.get(0).getId().equals(shopBaozi.getId())){
                return 0;
            }
        }
        shopBaozi.setDay(DateUtil.getWeek(createDate));
        //设置总收入（当日营业额）
        shopBaozi.setGetAllMoney(allMoney);
        //设置当日净利润
        BigDecimal putHouseMoney = shopBaozi.getPutHouseMoney() == null ? BigDecimal.valueOf(0) : shopBaozi.getPutHouseMoney();
        BigDecimal putMoney = shopBaozi.getPutMoney() == null ? BigDecimal.valueOf(0) : shopBaozi.getPutMoney();
        //净利润 总收入-房租-进货
        BigDecimal actualMoney = allMoney.subtract(putHouseMoney).subtract(putMoney);
        shopBaozi.setActualMoney(actualMoney);
        //geng_salary 净利润 * 1/3 向下取整
        double a = 1;
        double b = 3;
        double c = a/b;
        BigDecimal gengSalary = actualMoney.divide( BigDecimal.valueOf(3),0,BigDecimal.ROUND_DOWN).setScale(0,BigDecimal.ROUND_DOWN);
        //guo_salary 净利润 * 2/3 向下取整
        double d = 2;
        double e = 3;
        double f = d/e;
        BigDecimal guoSalary = actualMoney.divide( BigDecimal.valueOf(3),0,BigDecimal.ROUND_DOWN).multiply(BigDecimal.valueOf(2)).setScale(0,BigDecimal.ROUND_DOWN);
        shopBaozi.setGengSalary(gengSalary);
        shopBaozi.setGuoSalary(guoSalary);
        return shopBaoziMapper.updateShopBaozi(shopBaozi);
    }

    /**
     * 批量删除baozi
     * 
     * @param ids 需要删除的baozi主键
     * @return 结果
     */
    @Override
    public int deleteShopBaoziByIds(Long[] ids)
    {
        return shopBaoziMapper.deleteShopBaoziByIds(ids);
    }

    /**
     * 删除baozi信息
     * 
     * @param id baozi主键
     * @return 结果
     */
    @Override
    public int deleteShopBaoziById(Long id)
    {
        return shopBaoziMapper.deleteShopBaoziById(id);
    }

    /**
     * 查询包子铺 当前月份、当月营业额、当月进货金额、当月净利润
     *
     * @param shopBaozi
     * @return
     */
    @Override
    public BaoZiTongJiVo selectTongJi(ShopBaozi shopBaozi) {
        BaoZiTongJiVo vo = new BaoZiTongJiVo();
        vo = shopBaoziMapper.selectTongJi(shopBaozi);
        return vo;
    }

}

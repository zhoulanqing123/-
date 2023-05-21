package com.ruoyi.shop.huajia.service;

import java.util.List;

import com.ruoyi.shop.baozi.domain.ShopBaozi;
import com.ruoyi.shop.huajia.domain.ShopHuajia;
import com.ruoyi.shop.huajia.vo.HuaJiaTongJiVo;

/**
 * huajiaService接口
 * 
 * @author ruoyi
 * @date 2023-05-20
 */
public interface IShopHuajiaService 
{
    /**
     * 查询huajia
     * 
     * @param id huajia主键
     * @return huajia
     */
    public ShopHuajia selectShopHuajiaById(Long id);

    /**
     * 查询huajia列表
     * 
     * @param shopHuajia huajia
     * @return huajia集合
     */
    public List<ShopHuajia> selectShopHuajiaList(ShopHuajia shopHuajia);

    /**
     * 新增huajia
     * 
     * @param shopHuajia huajia
     * @return 结果
     */
    public int insertShopHuajia(ShopHuajia shopHuajia);

    /**
     * 修改huajia
     * 
     * @param shopHuajia huajia
     * @return 结果
     */
    public int updateShopHuajia(ShopHuajia shopHuajia);

    /**
     * 批量删除huajia
     * 
     * @param ids 需要删除的huajia主键集合
     * @return 结果
     */
    public int deleteShopHuajiaByIds(Long[] ids);

    /**
     * 删除huajia信息
     * 
     * @param id huajia主键
     * @return 结果
     */
    public int deleteShopHuajiaById(Long id);

    HuaJiaTongJiVo selectTongJi(ShopHuajia shopHuajia);
}

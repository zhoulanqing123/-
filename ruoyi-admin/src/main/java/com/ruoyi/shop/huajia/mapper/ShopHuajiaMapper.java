package com.ruoyi.shop.huajia.mapper;

import java.util.List;
import com.ruoyi.shop.huajia.domain.ShopHuajia;
import com.ruoyi.shop.huajia.vo.HuaJiaTongJiVo;

/**
 * huajiaMapper接口
 * 
 * @author ruoyi
 * @date 2023-05-20
 */
public interface ShopHuajiaMapper 
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
     * 删除huajia
     * 
     * @param id huajia主键
     * @return 结果
     */
    public int deleteShopHuajiaById(Long id);

    /**
     * 批量删除huajia
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopHuajiaByIds(Long[] ids);

    HuaJiaTongJiVo selectTongJi(ShopHuajia shopHuajia);
}

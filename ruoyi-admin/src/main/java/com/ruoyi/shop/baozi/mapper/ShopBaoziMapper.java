package com.ruoyi.shop.baozi.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.shop.baozi.domain.ShopBaozi;
import com.ruoyi.shop.baozi.vo.BaoZiTongJiVo;

/**
 * baoziMapper接口
 * 
 * @author ruoyi
 * @date 2023-05-20
 */
public interface ShopBaoziMapper 
{
    /**
     * 查询baozi
     * 
     * @param id baozi主键
     * @return baozi
     */
    public ShopBaozi selectShopBaoziById(Long id);

    /**
     * 查询baozi列表
     * 
     * @param shopBaozi baozi
     * @return baozi集合
     */
    public List<ShopBaozi> selectShopBaoziList(ShopBaozi shopBaozi);

    /**
     * 新增baozi
     * 
     * @param shopBaozi baozi
     * @return 结果
     */
    public int insertShopBaozi(ShopBaozi shopBaozi);

    /**
     * 修改baozi
     * 
     * @param shopBaozi baozi
     * @return 结果
     */
    public int updateShopBaozi(ShopBaozi shopBaozi);

    /**
     * 删除baozi
     * 
     * @param id baozi主键
     * @return 结果
     */
    public int deleteShopBaoziById(Long id);

    /**
     * 批量删除baozi
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopBaoziByIds(Long[] ids);

    BaoZiTongJiVo selectTongJi(ShopBaozi shopBaozi);

    List<Map> selectZheXianTongJi(ShopBaozi shopBaozi);
}

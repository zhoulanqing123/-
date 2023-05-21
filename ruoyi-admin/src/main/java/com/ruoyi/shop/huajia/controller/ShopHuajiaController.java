package com.ruoyi.shop.huajia.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.shop.baozi.domain.ShopBaozi;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.shop.huajia.domain.ShopHuajia;
import com.ruoyi.shop.huajia.service.IShopHuajiaService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * huajiaController
 * 
 * @author ruoyi
 * @date 2023-05-20
 */
@RestController
@RequestMapping("/shop/huajia")
public class ShopHuajiaController extends BaseController
{
    @Autowired
    private IShopHuajiaService shopHuajiaService;

    /**
     * 查询huajia列表
     */
    @PreAuthorize("@ss.hasPermi('shop:huajia:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopHuajia shopHuajia)
    {
        startPage();
        List<ShopHuajia> list = shopHuajiaService.selectShopHuajiaList(shopHuajia);
        return getDataTable(list);
    }

    /**
     * 导出huajia列表
     */
    @PreAuthorize("@ss.hasPermi('shop:huajia:export')")
    @Log(title = "huajia", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopHuajia shopHuajia)
    {
        List<ShopHuajia> list = shopHuajiaService.selectShopHuajiaList(shopHuajia);
        ExcelUtil<ShopHuajia> util = new ExcelUtil<ShopHuajia>(ShopHuajia.class);
        util.exportExcel(response, list, "huajia数据");
    }

    /**
     * 获取huajia详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:huajia:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(shopHuajiaService.selectShopHuajiaById(id));
    }

    /**
     * 新增huajia
     */
    @PreAuthorize("@ss.hasPermi('shop:huajia:add')")
    @Log(title = "huajia", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopHuajia shopHuajia)
    {
        return toAjax(shopHuajiaService.insertShopHuajia(shopHuajia));
    }

    /**
     * 修改huajia
     */
    @PreAuthorize("@ss.hasPermi('shop:huajia:edit')")
    @Log(title = "huajia", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopHuajia shopHuajia)
    {
        return toAjax(shopHuajiaService.updateShopHuajia(shopHuajia));
    }

    /**
     * 删除huajia
     */
    @PreAuthorize("@ss.hasPermi('shop:huajia:remove')")
    @Log(title = "huajia", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shopHuajiaService.deleteShopHuajiaByIds(ids));
    }

    /**
     * 统计数据
     */
    @PostMapping("tongJi")
    public AjaxResult tongJi(@RequestBody ShopHuajia shopHuajia)
    {
        return success(shopHuajiaService.selectTongJi(shopHuajia));
    }
}

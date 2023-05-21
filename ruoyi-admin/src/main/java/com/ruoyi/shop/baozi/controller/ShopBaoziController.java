package com.ruoyi.shop.baozi.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.shop.baozi.domain.ShopBaozi;
import com.ruoyi.shop.baozi.service.IShopBaoziService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * baoziController
 * 
 * @author ruoyi
 * @date 2023-05-20
 */
@RestController
@RequestMapping("/shop/baozi")
public class ShopBaoziController extends BaseController
{
    @Autowired
    private IShopBaoziService shopBaoziService;

    /**
     * 查询baozi列表
     */
    @PreAuthorize("@ss.hasPermi('shop:baozi:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopBaozi shopBaozi)
    {
        startPage();
        List<ShopBaozi> list = shopBaoziService.selectShopBaoziList(shopBaozi);
        return getDataTable(list);
    }

    /**
     * 导出baozi列表
     */
    @PreAuthorize("@ss.hasPermi('shop:baozi:export')")
    @Log(title = "baozi", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopBaozi shopBaozi)
    {
        List<ShopBaozi> list = shopBaoziService.selectShopBaoziList(shopBaozi);
        ExcelUtil<ShopBaozi> util = new ExcelUtil<ShopBaozi>(ShopBaozi.class);
        util.exportExcel(response, list, "baozi数据");
    }

    /**
     * 获取baozi详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:baozi:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(shopBaoziService.selectShopBaoziById(id));
    }

    /**
     * 新增baozi
     */
    @PreAuthorize("@ss.hasPermi('shop:baozi:add')")
    @Log(title = "baozi", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopBaozi shopBaozi)
    {
        return toAjax(shopBaoziService.insertShopBaozi(shopBaozi));
    }

    /**
     * 修改baozi
     */
    @PreAuthorize("@ss.hasPermi('shop:baozi:edit')")
    @Log(title = "baozi", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopBaozi shopBaozi)
    {
        return toAjax(shopBaoziService.updateShopBaozi(shopBaozi));
    }

    /**
     * 删除baozi
     */
    @PreAuthorize("@ss.hasPermi('shop:baozi:remove')")
    @Log(title = "baozi", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shopBaoziService.deleteShopBaoziByIds(ids));
    }

    /**
     * 统计数据
     */
    @PostMapping("tongJi")
    public AjaxResult tongJi(@RequestBody ShopBaozi shopBaozi)
    {
        return success(shopBaoziService.selectTongJi(shopBaozi));
    }
}

package com.ruoyi.shop.baozi.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * baozi对象 shop_baozi
 * 
 * @author ruoyi
 * @date 2023-05-20
 */
public class ShopBaozi extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 当天时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "当天时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM")
    private Date queryMonth;

    /** 当日星期 */
    @Excel(name = "当日星期")
    private String day;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String name;

    /** 支付宝收款金额 */
    @Excel(name = "支付宝收款金额")
    private BigDecimal getPayMoney;

    /** 微信收款金额 */
    @Excel(name = "微信收款金额")
    private BigDecimal getWechatMoney;

    /** 现金收款金额 */
    @Excel(name = "现金收款金额")
    private BigDecimal getMoney;

    /** 当日营业额（总收入） */
    @Excel(name = "当日营业额")
    private BigDecimal getAllMoney;

    /** 当日进货金额 */
    @Excel(name = "当日进货金额")
    private BigDecimal putMoney;

    /** 房租 */
    @Excel(name = "房租")
    private BigDecimal putHouseMoney;

    /** 当日净利润 */
    @Excel(name = "当日净利润")
    private BigDecimal actualMoney;

    /** 郭迎凤工资 */
    @Excel(name = "郭迎凤工资")
    private BigDecimal guoSalary;

    /** 耿世芳工资 */
    @Excel(name = "耿世芳工资")
    private BigDecimal gengSalary;

    /** 进货内容 */
    @Excel(name = "进货内容")
    private String putContent;

    public Date getQueryMonth() {
        return queryMonth;
    }

    public void setQueryMonth(Date queryMonth) {
        this.queryMonth = queryMonth;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }
    public void setDay(String day) 
    {
        this.day = day;
    }

    public String getDay() 
    {
        return day;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setGetPayMoney(BigDecimal getPayMoney) 
    {
        this.getPayMoney = getPayMoney;
    }

    public BigDecimal getGetPayMoney() 
    {
        return getPayMoney;
    }
    public void setGetWechatMoney(BigDecimal getWechatMoney) 
    {
        this.getWechatMoney = getWechatMoney;
    }

    public BigDecimal getGetWechatMoney() 
    {
        return getWechatMoney;
    }
    public void setGetMoney(BigDecimal getMoney) 
    {
        this.getMoney = getMoney;
    }

    public BigDecimal getGetMoney() 
    {
        return getMoney;
    }
    public void setGetAllMoney(BigDecimal getAllMoney) 
    {
        this.getAllMoney = getAllMoney;
    }

    public BigDecimal getGetAllMoney() 
    {
        return getAllMoney;
    }
    public void setPutMoney(BigDecimal putMoney) 
    {
        this.putMoney = putMoney;
    }

    public BigDecimal getPutMoney() 
    {
        return putMoney;
    }
    public void setPutHouseMoney(BigDecimal putHouseMoney) 
    {
        this.putHouseMoney = putHouseMoney;
    }

    public BigDecimal getPutHouseMoney() 
    {
        return putHouseMoney;
    }
    public void setActualMoney(BigDecimal actualMoney) 
    {
        this.actualMoney = actualMoney;
    }

    public BigDecimal getActualMoney() 
    {
        return actualMoney;
    }
    public void setGuoSalary(BigDecimal guoSalary) 
    {
        this.guoSalary = guoSalary;
    }

    public BigDecimal getGuoSalary() 
    {
        return guoSalary;
    }
    public void setGengSalary(BigDecimal gengSalary) 
    {
        this.gengSalary = gengSalary;
    }

    public BigDecimal getGengSalary() 
    {
        return gengSalary;
    }
    public void setPutContent(String putContent) 
    {
        this.putContent = putContent;
    }

    public String getPutContent() 
    {
        return putContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createDate", getCreateDate())
            .append("day", getDay())
            .append("name", getName())
            .append("getPayMoney", getGetPayMoney())
            .append("getWechatMoney", getGetWechatMoney())
            .append("getMoney", getGetMoney())
            .append("getAllMoney", getGetAllMoney())
            .append("putMoney", getPutMoney())
            .append("putHouseMoney", getPutHouseMoney())
            .append("actualMoney", getActualMoney())
            .append("guoSalary", getGuoSalary())
            .append("gengSalary", getGengSalary())
            .append("putContent", getPutContent())
            .append("remark", getRemark())
            .toString();
    }
}

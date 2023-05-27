package com.ruoyi.shop.huajia.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZheXianTongJiVo {

    private List<String> dataList;

    private List<String> getAllList;

    private List<String> getActualList;

    private List<String> shuoList;

    private List<String> guoList;

    private String getAllMoney;

    private String getActualMoney;

    private String putAllMoney;

    private String shuoSalary;

    private String guoSalary;

    private String allSalary;

    private String putHouseMoney;
}

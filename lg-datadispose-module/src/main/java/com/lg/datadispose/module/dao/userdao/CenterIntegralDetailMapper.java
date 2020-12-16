package com.lg.datadispose.module.dao.userdao;

import java.math.BigDecimal;
import java.util.Map;

import com.lg.datadispose.module.bean.po.CenterIntegralDetail;

public interface CenterIntegralDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(CenterIntegralDetail record);

    int insertSelective(CenterIntegralDetail record);

    CenterIntegralDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CenterIntegralDetail record);

    int updateByPrimaryKey(CenterIntegralDetail record);
    
    BigDecimal selectTodayScore(Map<String,Object> map);
}
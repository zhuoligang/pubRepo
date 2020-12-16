package com.lg.datadispose.module.dao.userdao;

import java.util.List;

import com.lg.datadispose.module.bean.po.CenterIntegralExplain;

public interface CenterIntegralExplainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CenterIntegralExplain record);

    int insertSelective(CenterIntegralExplain record);

    CenterIntegralExplain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CenterIntegralExplain record);

    int updateByPrimaryKey(CenterIntegralExplain record);
    
    List<CenterIntegralExplain> queryAll();
}
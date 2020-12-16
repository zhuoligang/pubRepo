package com.lg.datadispose.module.dao.userdao;

import java.util.Map;

import com.lg.datadispose.module.bean.po.CenterIntegralBase;

public interface CenterIntegralBaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(CenterIntegralBase record);

    int insertSelective(CenterIntegralBase record);

    CenterIntegralBase selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CenterIntegralBase record);

    int updateByPrimaryKey(CenterIntegralBase record);
    
    CenterIntegralBase selectByMap(Map<String, String> map);
}
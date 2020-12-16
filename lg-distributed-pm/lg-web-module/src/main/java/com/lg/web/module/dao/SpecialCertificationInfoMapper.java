package com.lg.web.module.dao;

import java.util.List;
import java.util.Map;

import com.lg.web.module.bean.po.SpecialCertificationInfo;

public interface SpecialCertificationInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpecialCertificationInfo record);

    int insertSelective(SpecialCertificationInfo record);

    SpecialCertificationInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpecialCertificationInfo record);

    int updateByPrimaryKey(SpecialCertificationInfo record);
    
    //
    List<SpecialCertificationInfo> selectByMap(Map<String,String> map);
}
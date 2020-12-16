package com.lg.datadispose.module.dao.userdao;

import com.lg.datadispose.module.bean.po.CenterViplevelBase;

public interface CenterViplevelBaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(CenterViplevelBase record);

    int insertSelective(CenterViplevelBase record);

    CenterViplevelBase selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CenterViplevelBase record);

    int updateByPrimaryKey(CenterViplevelBase record);
}
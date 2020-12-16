package com.bi.activity.dao;


import com.bi.activity.entity.Vcoinrecord;

public interface VcoinrecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Vcoinrecord record);

    int insertSelective(Vcoinrecord record);

    Vcoinrecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Vcoinrecord record);

    int updateByPrimaryKey(Vcoinrecord record);
    
    int newInsert(Vcoinrecord record);
}
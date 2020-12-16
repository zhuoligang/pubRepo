package com.bi.activity.dao;

import com.bi.activity.entity.MemberLog;

public interface MemberLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberLog record);

    int insertSelective(MemberLog record);

    MemberLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberLog record);

    int updateByPrimaryKey(MemberLog record);
}
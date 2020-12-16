package com.bi.activity.dao;

import java.util.Map;

import com.bi.activity.entity.Member;

public interface MemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
    
    int frozenMembers(Map<String, Object> map);
}
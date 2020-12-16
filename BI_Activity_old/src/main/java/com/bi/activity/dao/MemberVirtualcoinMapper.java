package com.bi.activity.dao;

import java.util.List;
import java.util.Map;

import com.bi.activity.dto.MemberVirtualcoinDto;
import com.bi.activity.entity.MemberVirtualcoin;

public interface MemberVirtualcoinMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberVirtualcoin record);

    int insertSelective(MemberVirtualcoin record);

    MemberVirtualcoin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberVirtualcoin record);

    int updateByPrimaryKey(MemberVirtualcoin record);
    
    List<MemberVirtualcoin> selectByMemberId(Map<String, Object> map);
    
    int newInsert(MemberVirtualcoin record);
    
    List<MemberVirtualcoinDto> selectMemberVirtualcoinDtosByMemberId(Map<String, Object> map);
}
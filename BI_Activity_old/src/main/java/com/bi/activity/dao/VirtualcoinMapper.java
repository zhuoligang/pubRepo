package com.bi.activity.dao;

import com.bi.activity.entity.Virtualcoin;

public interface VirtualcoinMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Virtualcoin record);

    int insertSelective(Virtualcoin record);

    Virtualcoin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Virtualcoin record);

    int updateByPrimaryKeyWithBLOBs(Virtualcoin record);

    int updateByPrimaryKey(Virtualcoin record);
}
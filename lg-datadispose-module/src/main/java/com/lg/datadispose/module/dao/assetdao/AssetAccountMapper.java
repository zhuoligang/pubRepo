package com.lg.datadispose.module.dao.assetdao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.lg.datadispose.module.bean.po.AssetAccount;

public interface AssetAccountMapper {
    int deleteByPrimaryKey(String id);

    int insert(AssetAccount record);

    int insertSelective(AssetAccount record);

    AssetAccount selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AssetAccount record);

    int updateByPrimaryKey(AssetAccount record);

	BigDecimal selectBtCountByUserId(@Param("userId")String userId);
}
package com.lg.datadispose.module.dao.userdao;

import org.apache.ibatis.annotations.Param;

import com.lg.datadispose.module.bean.po.CenterUserMain;
import com.lg.datadispose.module.exception.BusinessException;

public interface CenterUserMainMapper {
	int deleteByPrimaryKey(String id) throws BusinessException;

	int insert(CenterUserMain record) throws BusinessException;

	int insertSelective(CenterUserMain record) throws BusinessException;

	CenterUserMain selectByPrimaryKey(String id) throws BusinessException;

	int updateByPrimaryKeySelective(CenterUserMain record) throws BusinessException;

	int updateByPrimaryKey(CenterUserMain record) throws BusinessException;

	CenterUserMain selectLastMember(@Param("memberId") String memberId);

}
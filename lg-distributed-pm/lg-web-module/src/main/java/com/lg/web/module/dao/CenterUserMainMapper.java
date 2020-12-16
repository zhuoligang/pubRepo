package com.lg.web.module.dao;

import org.springframework.stereotype.Component;

import com.lg.web.module.bean.po.CenterUserMain;
import com.lg.web.module.exception.BusinessException;

@Component
public interface CenterUserMainMapper {
	int deleteByPrimaryKey(String id) throws BusinessException;

	int insert(CenterUserMain record) throws BusinessException;

	int insertSelective(CenterUserMain record) throws BusinessException;

	CenterUserMain selectByPrimaryKey(String id) throws BusinessException;

	int updateByPrimaryKeySelective(CenterUserMain record) throws BusinessException;

	int updateByPrimaryKey(CenterUserMain record) throws BusinessException;
	// 以下为新增
    CenterUserMain selectByMemberId(String memberId)throws BusinessException;
}
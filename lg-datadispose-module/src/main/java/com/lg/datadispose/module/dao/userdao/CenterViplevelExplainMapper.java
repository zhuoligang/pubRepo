package com.lg.datadispose.module.dao.userdao;

import java.util.List;

import com.lg.datadispose.module.bean.po.CenterViplevelExplain;

public interface CenterViplevelExplainMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(CenterViplevelExplain record);

	int insertSelective(CenterViplevelExplain record);

	CenterViplevelExplain selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CenterViplevelExplain record);

	int updateByPrimaryKey(CenterViplevelExplain record);

	List<CenterViplevelExplain> queryAll();
}
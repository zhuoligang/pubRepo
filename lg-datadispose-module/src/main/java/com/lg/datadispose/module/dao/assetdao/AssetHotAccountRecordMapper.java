package com.lg.datadispose.module.dao.assetdao;

import java.util.List;
import java.util.Map;

import com.lg.datadispose.module.bean.bo.Day30AlongBo;
import com.lg.datadispose.module.bean.po.AssetHotAccountRecord;

public interface AssetHotAccountRecordMapper {
	int deleteByPrimaryKey(String id);

	int insert(AssetHotAccountRecord record);

	int insertSelective(AssetHotAccountRecord record);

	AssetHotAccountRecord selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(AssetHotAccountRecord record);

	int updateByPrimaryKey(AssetHotAccountRecord record);

	//获取最近30天各个币种成交总数量
	List<Day30AlongBo> getDay30AlongBo(Map<String,Object> map);
}
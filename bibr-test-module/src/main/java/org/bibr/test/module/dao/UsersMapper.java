package org.bibr.test.module.dao;

import org.bibr.test.module.bean.po.Users;

public interface UsersMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Users record);

	int insertSelective(Users record);

	Users selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Users record);

	int updateByPrimaryKey(Users record);
}
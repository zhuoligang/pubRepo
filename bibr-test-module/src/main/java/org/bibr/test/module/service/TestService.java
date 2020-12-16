package org.bibr.test.module.service;

import java.util.Map;

import org.bibr.test.module.bean.po.Users;
import org.bibr.test.module.dao.UsersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	private final static Logger logger = LoggerFactory.getLogger(TestService.class);
	@Autowired
	private UsersMapper usersMapper;
	
	public Users selectUser(Map<String, Object> map) {
		Users users = null;
		try {
			Integer id = Integer.parseInt(map.get("id").toString());
			users = this.usersMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return users;
	}

}

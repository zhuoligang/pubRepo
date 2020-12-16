package com.lg.datadispose.module.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lg.datadispose.module.constant.AuthContants;
import com.lg.datadispose.module.dao.userdao.UserMapper;
import com.lg.datadispose.module.domain.User;
import com.lg.datadispose.module.exception.BusinessException;
import com.lg.datadispose.module.util.DataDisposeUtil;

@Service
public class UserService {
	private final static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserMapper userMapper;
	
	public String saveUser(User user) {
		try {
			if (user == null) {
				throw new BusinessException(AuthContants.CODE_500, AuthContants.REQUEST_PARAMETER_NOT_NULL);
			}
			Integer count = this.userMapper.countByPhoneOrEmail(user.getPhone(),
					user.getPhone());
			if (count >= 1) {
				throw new BusinessException(AuthContants.CODE_500, AuthContants.USER_IS_EXIST);
			}
			String id = DataDisposeUtil.createUUId();
			user.setId(id);
			user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
			user.setDisable(AuthContants.DISABLE_CODE_0);
			user.setCreateTime(new Date());
			this.userMapper.insertSelective(user);
			return id;
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			logger.error("saveUser?" + JSONObject.toJSONString(user));
			throw new BusinessException(AuthContants.CODE_500, e.getMessage());
		} catch (Exception e) {
			logger.error(AuthContants.USER_SAVE_ERROR, e);
			logger.error("saveUser?" + JSONObject.toJSONString(user));
			throw new BusinessException(AuthContants.CODE_500, e.getMessage());
		}
	}
}

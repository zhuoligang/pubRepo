package com.lg.datadispose.module.dao.userdao;

import com.lg.datadispose.module.domain.User;
import com.lg.datadispose.module.exception.BusinessException;

public interface UserMapper {
	
    int deleteByPrimaryKey(String id) throws BusinessException;

    int insert(User record) throws BusinessException;

    int insertSelective(User record) throws BusinessException;

    User selectByPrimaryKey(String id) throws BusinessException;

    int updateByPrimaryKeySelective(User record) throws BusinessException;

    int updateByPrimaryKey(User record) throws BusinessException;
    
    /**
     * 根据用户手机，邮箱查用户
     * 
     * @param username
     * @return
     */
    User selectByPhoneOrEmail(String phone, String email);
 
    /**
     * 根据用户手机，邮箱查用户count
     * 
     * @param phone
     * @param email
     * @return
     */
    int countByPhoneOrEmail(String phone, String email);
    
    /**
     * 根据用户手机，邮箱查用户count
     * 
     * @param phone
     * @param email
     * @return
     */
    int countByPhone(String phone);

	void delMobile(String id);

	void delEmail(String id);
    
}
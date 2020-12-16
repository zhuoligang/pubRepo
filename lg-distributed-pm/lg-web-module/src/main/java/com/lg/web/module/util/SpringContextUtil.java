/**
 * 
 */
package com.lg.web.module.util;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ServletContextAware;

@Component
public class SpringContextUtil implements ApplicationContextAware,ServletContextAware {
    private static ApplicationContext applicationContext = null;
    
    private static ServletContext application = null;
    
	@Override
	public void setServletContext(ServletContext servletContext) {
		SpringContextUtil.application = servletContext;
	}
    
	public static ServletContext getServletContext(){
		return application;
	}
	
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static Object getBean(Class<?> c) {
        return applicationContext.getBean(c);
    }

    public static DataSourceTransactionManager getTransactionManager() {
        ApplicationContext appCtx = getApplicationContext();
        DataSourceTransactionManager transactionManager = appCtx.getBean("transactionManager",
                DataSourceTransactionManager.class);
        return transactionManager;
    }

    public static TransactionStatus getStatus(DataSourceTransactionManager transactionManager) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        return status;
    }

}
package com.lg.datadispose.module.dbsourcecfg;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)//是为了解决多数据源引起的循环引用问题
@MapperScan(basePackages = {UserDataSourceConfig.DAOPACKAGE}, sqlSessionFactoryRef = "userSqlSessionFactory")
//上面的@MapperScan,用于spring扫描dao和mapper，sqlSessionFactoryRef指定了用哪个“数据工厂”，在上面工厂的定义时，我们指定了xml文件所在的位置
public class UserDataSourceConfig {
	static final String DAOPACKAGE = "com.lg.datadispose.module.dao.userdao";//dao所在的包，用于注解方式请求数据库
	static final String MAPPACKAGE = "classpath:mapper/usermapper/*.xml";//mapper所在的包 ，用于xml方式请求数据库

	@Bean(name = "userDataSource")
	@Primary//设置其为默认数据源
	@ConfigurationProperties(prefix = "spring.datasource.usermysql")
	public DataSource rushDataSource() {
		return DataSourceBuilder.create().build();
	}
 
	@Bean(name = "userTransactionManager")
	@Primary
	public DataSourceTransactionManager rushTransactionManager() {
		return new DataSourceTransactionManager(rushDataSource());
	}

	@Bean(name = "userSqlSessionFactory")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.usermysql")
	public SqlSessionFactory rushSqlSessionFactory(@Qualifier("userDataSource") DataSource rushDataSource)
			throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(rushDataSource);
		sessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(UserDataSourceConfig.MAPPACKAGE));
		return sessionFactory.getObject();
	}

}

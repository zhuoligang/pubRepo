package com.lg.datadispose.module.dbsourcecfg;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@MapperScan(basePackages = {AssetDataSourceConfig.DAOPACKAGE}, sqlSessionFactoryRef = "assetSqlSessionFactory")
//上面的@MapperScan,用于spring扫描dao和mapper，sqlSessionFactoryRef指定了用哪个“数据工厂”，在上面工厂的定义时，我们指定了xml文件所在的位置
public class AssetDataSourceConfig {
	static final String DAOPACKAGE = "com.lg.datadispose.module.dao.assetdao";//dao所在的包，用于注解方式请求数据库
	static final String MAPPACKAGE = "classpath:mapper/assetmapper/*.xml";//mapper所在的包 ，用于xml方式请求数据库

	@Bean(name = "assetDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.druid.assetmysql")
	public DataSource assetDataSource() {
		return new DruidDataSource();
	}
 
	@Bean(name = "assetTransactionManager")
	public DataSourceTransactionManager assetTransactionManager() {
		return new DataSourceTransactionManager(assetDataSource());
	}

	@Bean(name = "assetSqlSessionFactory")
	@ConfigurationProperties(prefix = "spring.datasource.druid.assetmysql")
	public SqlSessionFactory assetSqlSessionFactory(@Qualifier("assetDataSource") DataSource assetDataSource)
			throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(assetDataSource);
		sessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(AssetDataSourceConfig.MAPPACKAGE));
		return sessionFactory.getObject();
	}

}

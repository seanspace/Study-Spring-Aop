package com.bin.spring.jdbctemplate;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * 使用JdbcDaoSupport,并不推荐使用这种方法.
 * 这种方式不能使用注解.必须注入templete或dataSource,所以需要在xml配置.
 * 当然也可以用一个别的方法.如下面已经注释掉的部分.
 */
public class CustomerDao extends JdbcDaoSupport {
	
	/**
	 * 使用这种方法实现注解数据源.
	 */
//	@Autowired
//	public void setDataSource2(DataSource dataSource){
//		setDataSource(dataSource);
//	}
}

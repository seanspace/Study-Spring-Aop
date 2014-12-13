package com.bin.spring.jdbctemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class JDBCTest {
	
	private ApplicationContext ctx = null ;
	private JdbcTemplate jdbcTemplate ;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate = null ;
	
	{
		ctx = new ClassPathXmlApplicationContext("jdbc-templete.xml") ;
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate") ;
		namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) ctx.getBean("namedParameterJdbcTemplate") ;
	}
	
	@Test
	public void testDataSource() throws SQLException{
		DataSource dataSource = ctx.getBean(DataSource.class) ;
		System.out.println(dataSource.getConnection());
	}
	
	/**
	 * 执行,insert,update,delete都可以.
	 */
	@Test
	public void testUpdate(){
		String sql = "Update orders set order_name = ? where order_id = ?" ;
		jdbcTemplate.update(sql,"order",7) ;
	}
	
	/**
	 * 执行批量更新:批量insert,update,delete
	 * 第二个参数是Object[]的List类型:因为修改一条记录需要一个Object数据.那么多条就要多个数组;
	 */
	@Test
	public void testBatchUpdate(){
		String sql = "insert into orders(order_name,customer_id) values(?,?)" ;
		List<Object[]> batchArgs = new ArrayList<>() ;
		batchArgs.add(new Object[]{"AA",1}) ;
		batchArgs.add(new Object[]{"AA",1}) ;
		batchArgs.add(new Object[]{"AA",1}) ;
		batchArgs.add(new Object[]{"AA",1}) ;
		batchArgs.add(new Object[]{"AA",1}) ;
		batchArgs.add(new Object[]{"AA",1}) ;
		jdbcTemplate.batchUpdate(sql,batchArgs) ;
	}
	
	/**
	 * 从数据库中获取一条记录,实际获得一个对象;
	 * 注意不是调用queryForObject(String sql, Class<Oerder> requiredType, Object... args)
	 * 而是调用:queryForObject(sql, rowMapper , 1)
	 * 	1. 其中的RowMapper指定如何去映射结果集的行,常用的实现类为BeanPropertyRowMapper
	 * 	2. 使用SQL中列的别名完成和类的属性名的映射.例如:order_name ordername ;实际上别名order_name也可以自动匹配到orderName属性.可能是下划线会自动去掉.
	 *  3. 不支持级联属性.JdbcTemplete到底是一个Jdbc的小工具,而不是ORM框架.
	 */
	@Test
	public void testQueryForObject(){
		String sql = "select order_name, order_id ,customer_id from orders where order_id = ?" ;
		RowMapper<Oerder> rowMapper = new BeanPropertyRowMapper<>(Oerder.class) ;
		Oerder order = jdbcTemplate.queryForObject(sql, rowMapper , 1) ;
		
		System.out.println(order);
	}
	
	/**
	 * 查到实体类的集合.
	 * 注意调用的不是queryForList方法.
	 */
	@Test
	public void testQueryForList(){
		String sql = "select order_name,order_id,customer_id from orders where order_id > ?" ;
		RowMapper<Oerder> rowMapper = new BeanPropertyRowMapper<>(Oerder.class) ;
		List<Oerder> orders = jdbcTemplate.query(sql, rowMapper,2) ;
		System.out.println(orders);
	}
	
	/**
	 * 获取单个列的值,或做统计查询.
	 * 
	 */
	@Test
	public void testQueryForObject2(){
		String sql = "select count(id) From orders" ;
		long count = jdbcTemplate.queryForLong(sql,Long.class) ;
		System.out.println(count);
	}
	
	/**
	 * 可以为参数取名字.
	 * 1. 好处: 若有多个参数,则不用再去对应位置,直接对应参数名,便于维护.
	 * 2. 缺点: 教为麻烦.
	 */
	@Test
	public void testNamedParameterJdbcTemplate(){
		String sql = "insert into orders(order_name,customer_id) values(:on,:cust_id)" ;
		Map<String, Object> paramMap = new HashMap<String, Object>() ;
		paramMap.put("on", 20) ;
		paramMap.put("cust_id", 1) ;
		namedParameterJdbcTemplate.update(sql, paramMap) ;
	}
	
	/**
	 * 使用具名参数时,可以使用update(sql,parameterSource)方法.参数名需要与javaBean对象属性名一致.
	 * 有点类似于Hibernate.
	 */
	@Test
	public void testNamedParameterJdbcTemplate2(){
		String sql = "insert into orders(order_name) values(:orderName)" ;
		Oerder oerder = new Oerder() ;
		oerder.setOrderName("order_name");
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(oerder) ;
		
		namedParameterJdbcTemplate.update(sql,parameterSource) ;
	}

}

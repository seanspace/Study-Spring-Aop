package com.bin.spring.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 这里就是实际开发时使用jdbcTemplate的用法.
 * 把template注入到当前dao
 */
@Repository
public class OrderDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	public Oerder get(Integer id){
		return null;
	}

}

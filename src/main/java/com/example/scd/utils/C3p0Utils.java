package com.example.scd.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//使用C3p0连接池获取连接数据库
public class C3p0Utils {
	private static DataSource ds;
	static{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		ds = dataSource;
	}
	public static DataSource getDs(){
		return ds;
	}

	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
}

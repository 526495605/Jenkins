package com.ykyy.server;

import com.ykyy.server.bean.UserBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

@SpringBootApplication
@MapperScan("com.ykyy.server.dao")
public class DemoApplication {

	public static void main(String[] args) {

		System.out.println(new UserBean(1, "mc", "123132", new Date(54465456), "123", "1231"));
		SpringApplication.run(DemoApplication.class, args);
	}
}

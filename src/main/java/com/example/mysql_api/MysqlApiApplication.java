package com.example.mysql_api;

import com.example.atomic.AtomicStarter;
import com.example.client.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
public class MysqlApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqlApiApplication.class, args);
//		Server server = new Server(8000);
//		AtomicStarter starter = new AtomicStarter();
	}

}

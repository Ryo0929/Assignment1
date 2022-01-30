package com.example.mysql_api;

import com.example.client.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MysqlApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqlApiApplication.class, args);
		Server server = new Server(5000);
	}

}

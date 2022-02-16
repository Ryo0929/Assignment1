package com.example.mysql_api;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class grpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(8050).addService(new ItemServiceImpl()).build();

        server.start();
        SpringApplication.run(MysqlApiApplication.class, args);
        server.awaitTermination();
    }
}

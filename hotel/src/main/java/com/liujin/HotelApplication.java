package com.liujin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 刘锦
 * @date 2020-1-28
 */
@SpringBootApplication
@MapperScan("com.liujin.mapper")
@EnableTransactionManagement
@EnableScheduling // 定时器启动
public class HotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelApplication.class, args);
    }

}

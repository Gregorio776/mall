package cn.edu.tsu.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Gregorio
 * @date 2020/4/28 10:40
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerAdmin {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerAdmin.class,args);
    }
}

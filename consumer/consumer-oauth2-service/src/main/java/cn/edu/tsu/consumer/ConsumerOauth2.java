package cn.edu.tsu.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Gregorio
 * @date 2020/4/28 17:31
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerOauth2 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOauth2.class,args);
    }
}

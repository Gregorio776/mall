package cn.edu.tsu.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Gregorio
 * @date 2020/4/28 15:20
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "cn.edu.tsu.provider.mapper")
public class ProviderAdmin {
    public static void main(String[] args) {
        SpringApplication.run(ProviderAdmin.class,args);
    }
}

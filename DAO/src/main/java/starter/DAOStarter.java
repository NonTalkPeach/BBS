package eurekaserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("dap")
public class DAOStarter {
    public static void main(String[] args) {
        SpringApplication.run(DAOStarter.class,args);
    }
}

package auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableEurekaClient
public class AuthServiceStarter {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceStarter.class,args);
    }
}

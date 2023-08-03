package com.apihub.customerinfointegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CustomerInfoIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerInfoIntegrationApplication.class, args);
    }

}

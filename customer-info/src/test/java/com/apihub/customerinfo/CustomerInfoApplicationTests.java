package com.apihub.customerinfo;

import com.apihub.customerinfo.abastruction.CustomerService;
import com.apihub.customerinfo.client.IntegrationClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerInfoApplicationTests {

    @Autowired
    private IntegrationClient integrationClient;

    @Autowired
    private CustomerService customerService;

    @Test
    void contextLoads() {
    }

    @Test
    void integrateClient(){
        assertThat(integrationClient.getCustomerInfo()).isNotNull();
    }

    @Test
    void getCustomerInfo(){
        assertThat(customerService.getInfo()).isNotNull();
    }



}

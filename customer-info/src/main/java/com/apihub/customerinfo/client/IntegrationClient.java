package com.apihub.customerinfo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Nazim Uddin Asif
 * @Since 1.0.0
 */
@FeignClient(name = "API-GATEWAY")
public interface IntegrationClient {
    @RequestMapping(value = "/api/v1/integration/customer/info", method = RequestMethod.GET)
    ResponseEntity<String> getCustomerInfo();

    @RequestMapping(value = "/api/v1/integration/customer/address", method = RequestMethod.GET)
    ResponseEntity<String> getCustomerAddress();
}

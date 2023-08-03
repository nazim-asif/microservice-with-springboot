package com.apihub.customerinfo.controller;

import com.apihub.customerinfo.client.IntegrationClient;
import com.apihub.customerinfo.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nazim Uddin Asif
 * @Since 1.0.0
 */
@RestController
@RequestMapping(ApiUtils.BASE_CLIENT_URL)
@RequiredArgsConstructor
public class IntegrationController {
    private final IntegrationClient integrationClient;

    @RequestMapping(value = "/customer/info", method = RequestMethod.GET)
    ResponseEntity<String> getCustomerInfo(){
        ResponseEntity<String> response;
        try {
            response = integrationClient.getCustomerInfo();
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

        return response;
    }
}

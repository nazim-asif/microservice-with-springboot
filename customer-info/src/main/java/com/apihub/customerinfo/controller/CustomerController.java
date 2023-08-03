package com.apihub.customerinfo.controller;

import com.apihub.customerinfo.abastruction.CustomerService;
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
@RequiredArgsConstructor
@RequestMapping(ApiUtils.BASE_URL)
public class CustomerController {
    private final CustomerService customerService;
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseEntity<String> getCustomerInfo(){
        return ResponseEntity.ok(customerService.getInfo());
    }
}

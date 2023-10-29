package com.apihub.customerinfo.controller;

import org.springframework.http.ResponseEntity;

/**
 * @author Nazim Uddin Asif
 * @Since 1.0.0
 */
public class BaseController {

    public ResponseEntity<String> fallbackForCustomerInfo( ){
        return ResponseEntity.ok("Service is not available");
    }
}

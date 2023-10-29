package com.apihub.customerinfo.utils;

import org.springframework.http.ResponseEntity;


/**
 * @author Nazim Uddin Asif
 * @Since 1.0.0
 */
public class FaultToleranceUtils {
    public static ResponseEntity<String> fallbackForCircuitBreaker(Throwable e){
        return ResponseEntity.ok("Service is not available");
    }

}

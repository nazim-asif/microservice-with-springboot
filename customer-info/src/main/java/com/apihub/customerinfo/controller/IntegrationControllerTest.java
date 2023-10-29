package com.apihub.customerinfo.controller;

import com.apihub.customerinfo.client.IntegrationClient;
import com.apihub.customerinfo.utils.ApiUtils;

import com.apihub.customerinfo.utils.CustomCircuitBreakerService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
public class IntegrationControllerTest extends BaseController{
    private final IntegrationClient integrationClient;
    private final CustomCircuitBreakerService circuitBreakerService;


    /*@RequestMapping(value = "/customer/info", method = RequestMethod.GET)
//    @CircuitBreaker(name = "customer-info", fallbackMethod = "fallbackForCustomerInfo")
    ResponseEntity<String> getCustomerInfo(){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("abc");
        return circuitBreaker.run(()-> {
            ResponseEntity<String> response;
            try {
                response = integrationClient.getCustomerInfo();
            }catch (Exception ex){
                throw new RuntimeException(ex.getMessage());
            }

            return response;
        },Throwable -> fallbackForCustomerInfo());
//        circuitBreakerService.createCircuitBreaker("customer-info",50,5, () -> {
//            ResponseEntity<String> response;
//            try {
//                response = integrationClient.getCustomerInfo();
//            }catch (Exception ex){
//                throw new RuntimeException(ex.getMessage());
//            }
//
//            return response;
//        });
//        return null;
//        return circuitBreakerService.executeWithCircuitBreaker("customer-info", () -> {
//            ResponseEntity<String> response;
//            try {
//                response = integrationClient.getCustomerInfo();
//            }catch (Exception ex){
//                throw new RuntimeException(ex.getMessage());
//            }
//
//            return response;
//        });

    }*/


    @RequestMapping(value = "/customer/address", method = RequestMethod.GET)
    @CircuitBreaker(name = "customer-address", fallbackMethod = "fallbackForCustomerInfo")
    ResponseEntity<String> getCustomerAddress(){
        circuitBreakerService.createCircuitBreaker("customer-address",50,5);
        return circuitBreakerService.executeWithCircuitBreaker("customer-address", () -> {
            ResponseEntity<String> response;
            try {
                response = integrationClient.getCustomerAddress();
            }catch (Exception ex){
                throw new RuntimeException(ex.getMessage());
            }

            return response;
        });

    }
    public ResponseEntity<String> fallbackForCustomerInfo( Throwable e ){
        return ResponseEntity.ok("Service is not available");
    }

}

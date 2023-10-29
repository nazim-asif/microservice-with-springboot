package com.apihub.customerinfointegration.service;

import com.apihub.customerinfointegration.abstruction.CustomerService;
import org.springframework.stereotype.Service;

/**
 * @author Nazim Uddin Asif
 * @Since 1.0.0
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public String getInfo(){
        return "Hello this is customer info from Customer-info-integration service";
    }

    @Override
    public String getAddress() {
        return "XXX Towers Block G, Road No. 2, Plot No. 4. Lane No. 4, XXX Housing Estate XXX, Bangladesh";
    }
}

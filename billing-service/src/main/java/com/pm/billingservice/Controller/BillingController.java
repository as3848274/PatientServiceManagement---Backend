package com.pm.billingservice.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    @GetMapping("/status/{patientId}")
    public String getBillStatus(@PathVariable UUID patientId){
        return "Billing status of patient with id " + patientId + " : PAID";
    }
}

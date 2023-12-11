package com.pracloomcompany.pracloom.Controller;

import com.pracloomcompany.pracloom.Service.SubscriptionService;
import com.pracloomcompany.pracloom.dto.SubscriptionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    public int createSubscription(SubscriptionDTO subscriptionDTO){
       return this.subscriptionService.createSubscription(subscriptionDTO);
    }

}

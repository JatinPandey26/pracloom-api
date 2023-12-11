package com.pracloomcompany.pracloom.Controller;

import com.pracloomcompany.pracloom.Entities.SubscriptionType;
import com.pracloomcompany.pracloom.Service.Impl.SubscriptionTypeServiceImpl;
import com.pracloomcompany.pracloom.dto.SubscriptionTypeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/subscriptionTypes")
public class SubscriptionTypeController {
    private final SubscriptionTypeServiceImpl subscriptionTypeService;
    @GetMapping("/all")
    public ResponseEntity<List<SubscriptionType>> getSubscriptionTypes(){
        List<SubscriptionType> subscriptionTypes = this.subscriptionTypeService.getSubscriptionTypes();
        return ResponseEntity.ok(subscriptionTypes);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSubscriptionType(@RequestBody SubscriptionTypeRequest subscriptionType){
        this.subscriptionTypeService.createSubscriptionType(subscriptionType);
        return ResponseEntity.ok("Subscription Type Created");
    }

}

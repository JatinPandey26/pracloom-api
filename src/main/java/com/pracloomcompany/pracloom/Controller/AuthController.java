package com.pracloomcompany.pracloom.Controller;

import com.pracloomcompany.pracloom.Service.AuthenticationService;
import com.pracloomcompany.pracloom.dto.AuthenticationRequest;
import com.pracloomcompany.pracloom.dto.AuthenticationResponse;
import com.pracloomcompany.pracloom.dto.CustomerDTO;
import com.pracloomcompany.pracloom.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final AuthenticationService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){

        return ResponseEntity.ok(authService.register(request));
    }


    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) throws Exception {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/profile")
    public ResponseEntity<CustomerDTO> getProfile(

    ) throws Exception {

        return ResponseEntity.ok(authService.getProfile());
    }

}

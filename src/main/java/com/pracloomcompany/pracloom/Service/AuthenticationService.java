package com.pracloomcompany.pracloom.Service;

import com.pracloomcompany.pracloom.Entities.Customer;
import com.pracloomcompany.pracloom.Mapper.CustomerMapper;
import com.pracloomcompany.pracloom.Repository.CustomerRepository;
import com.pracloomcompany.pracloom.dto.AuthenticationRequest;
import com.pracloomcompany.pracloom.dto.AuthenticationResponse;
import com.pracloomcompany.pracloom.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    public AuthenticationResponse register(RegisterRequest request) {
        log.info(request.toString());

        Optional<Customer> customerCheck = this.customerRepository.findByEmail(request.getEmail());

        if(customerCheck.isPresent()) {
            return new AuthenticationResponse("","Customer with "+ request.getEmail() + " already present");
        }

        Customer customer = this.customerMapper.toEntity(request);
        customer.setPassword(this.passwordEncoder.encode(customer.getPassword()));
        Customer savedCustomer = this.customerRepository.save(customer);
        log.info("customer saved with id : {}" , savedCustomer.getId());

        String jwtToken = this.jwtService.generateJwtToken(customer);

        return new AuthenticationResponse(jwtToken,"Success");
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
        log.info(request.toString());
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Customer customer = this.customerRepository.findByEmail(request.getEmail()).orElseThrow(() -> new Exception("user not found with email : " + request.getEmail()));
        String jwtToken = this.jwtService.generateJwtToken(customer);
        return new AuthenticationResponse(jwtToken,"success");
    }
}

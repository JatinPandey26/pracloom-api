package com.pracloomcompany.pracloom.Config;


import com.pracloomcompany.pracloom.Interceptor.TenantContext;
import com.pracloomcompany.pracloom.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Order(2)
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter  {

    private final JwtService jwtService;
    private final UserDetailsService customerDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String customerEmail;

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            log.info("Authheader not found");
            filterChain.doFilter(request,response);
            return;
        }

        jwtToken = authHeader.substring(7);

        if(jwtToken.equals("null")){
            log.info("jwtToken is empty");
            filterChain.doFilter(request,response);
            return;
        }


        customerEmail = jwtService.extractCustomerEmail(jwtToken);

        if(customerEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails customerDetails = this.customerDetailsService.loadUserByUsername(customerEmail);
            log.info("customerDetails : {}",customerDetails);
            if(jwtService.isTokenValid(jwtToken,customerDetails)){
                // update securityContext

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                  customerDetails,
                  null,
                  customerDetails.getAuthorities()
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);

                // resolve tenant after authentication
                HttpServletRequest req = (HttpServletRequest) request;
                String tenantName = req.getHeader("X-TenantID");
                TenantContext.setCurrentTenant(tenantName);

                filterChain.doFilter(request,response);
            }
            else  {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("JWT expired or not valid");
            }
        }

    }
}

package com.teste.integracao.inttest.controller;

import com.teste.integracao.inttest.auth.JwtUtil;
import com.teste.integracao.inttest.domain.User;
import com.teste.integracao.inttest.domain.request.LoginRequest;
import com.teste.integracao.inttest.domain.response.LoginResponse;
import com.teste.integracao.inttest.exception.service.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;


    private JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginRequest loginReq) {
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginReq.getEmail(),
                            loginReq.getPassword()
                    )
            );

            final String email = authentication.getName();
            final User user = new User(email, "");
            final String token = jwtUtil.createToken(user);
            final LoginResponse loginRes = new LoginResponse(email, token);

            return ResponseEntity.ok(loginRes);

        } catch (BadCredentialsException e) {
            throw new ApplicationException("Invalid username or password", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

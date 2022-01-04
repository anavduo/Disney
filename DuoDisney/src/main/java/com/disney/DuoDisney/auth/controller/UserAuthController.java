package com.disney.DuoDisney.auth.controller;

import com.disney.DuoDisney.auth.dto.AuthRequestDTO;
import com.disney.DuoDisney.auth.dto.AuthResponseDTO;
import com.disney.DuoDisney.auth.dto.UserDTO;
import com.disney.DuoDisney.auth.service.JwtUtils;
import com.disney.DuoDisney.auth.service.UserDetailsCustomService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@author aduo

@RestController
@RequestMapping("/auth")
public class UserAuthController {
 private UserDetailsCustomService userDetailsCustomService;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtTokenUtil;

    public UserAuthController(UserDetailsCustomService userDetailsCustomService, AuthenticationManager authenticationManager, JwtUtils jwtTokenUtil) {
        this.userDetailsCustomService = userDetailsCustomService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> signup(@RequestBody UserDTO user) {
        this.userDetailsCustomService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponseDTO> signin(@RequestBody AuthRequestDTO authRequest) throws Exception {
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new Exception("Bad username or password", e);
        }

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponseDTO(jwt));
    }
}

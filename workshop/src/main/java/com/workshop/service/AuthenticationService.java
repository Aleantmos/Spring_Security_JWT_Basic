package com.workshop.service;

import com.workshop.model.dto.AuthenticationRequest;
import com.workshop.model.dto.AuthenticationResponse;
import com.workshop.model.dto.MyUserDetails;
import com.workshop.model.dto.RegisterRequest;
import com.workshop.model.entity.User;
import com.workshop.model.enums.Role;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userService.saveUser(user);

        MyUserDetails myUserDetails = userService.mapUserToMyUserDetails(user);

        var jwtToken = jwtService.generateToken(myUserDetails);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userService.findUserByEmail(request.getEmail());

        MyUserDetails myUserDetails = userService.mapUserToMyUserDetails(user);

        var jwtToken = jwtService.generateToken(myUserDetails);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

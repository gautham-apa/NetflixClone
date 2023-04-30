package com.hari.netflix.service;

import com.hari.netflix.auth.AuthenticationResponse;
import com.hari.netflix.auth.UserAuthRole;
import com.hari.netflix.dto.AdminRegistrationRequestDto;
import com.hari.netflix.dto.UserLoginRequestDto;
import com.hari.netflix.dto.UserRegistrationRequestDto;
import com.hari.netflix.dao.UserDAO;
import com.hari.netflix.pojo.NetflixUserDetails;
import com.hari.netflix.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    public ResponseEntity register(UserRegistrationRequestDto request) {
        if(userDAO.findUser(request.getEmail()) != null) {
            return new ResponseEntity("User with email already exists", HttpStatus.BAD_REQUEST);
        }
        User user = new User(request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getName(),
                request.getMobileNumber(),
                UserAuthRole.USER,
                request.getPrimaryAddress(),
                Integer.valueOf(request.getAge())
        );
        userDAO.persist(user);
        return new ResponseEntity("User created successfully", HttpStatus.CREATED);
    }

    public ResponseEntity registerAdmin(AdminRegistrationRequestDto request) {
        if(userDAO.findUser(request.getEmail()) != null) {
            return new ResponseEntity("User with email already exists", HttpStatus.BAD_REQUEST);
        }
        if(request.getRegistrationToken() == null || request.getRegistrationToken().isBlank()) {
            return new ResponseEntity("Registration token missing", HttpStatus.BAD_REQUEST);
        }
        User admin = new User(request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getName(),
                request.getMobileNumber(),
                UserAuthRole.ADMIN,
                request.getPrimaryAddress(),
                Integer.valueOf(request.getAge())
        );
        userDAO.persist(admin);
        return new ResponseEntity("Admin created successfully", HttpStatus.CREATED);
    }

    public ResponseEntity login(UserLoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userDAO.findUser(request.getEmail());
        if(user == null) {
            return new ResponseEntity("User with given email not found", HttpStatus.UNAUTHORIZED);
        }
        String jwtToken = jwtService.generateJWT(null, new NetflixUserDetails(user));
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtToken, "Login Successful");
        return new ResponseEntity(authenticationResponse, HttpStatus.OK);
    }
}

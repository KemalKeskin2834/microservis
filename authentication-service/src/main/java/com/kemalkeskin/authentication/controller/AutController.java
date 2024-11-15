package com.kemalkeskin.authentication.controller;

import com.kemalkeskin.authentication.business.Dtos.LoginDto;
import com.kemalkeskin.authentication.business.Dtos.RegisterDto;
import com.kemalkeskin.authentication.model.Role;
import com.kemalkeskin.authentication.model.User;
import com.kemalkeskin.authentication.repository.RoleRepository;
import com.kemalkeskin.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AutController {


    private AuthenticationManager authenticationManager;
    private  UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AutController(AuthenticationManager authenticationManager, UserRepository userRepository,
                         RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    public ResponseEntity<String>register(@RequestBody RegisterDto registerDto){

        if(userRepository.existsByUserName(registerDto.getUserName())){
            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
        }

        User user=new User();
        user.setUserName(registerDto.getUserName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role role=roleRepository.findByRole("USER").get();
        user.setRoles(Collections.singletonList(role));

        userRepository.save(user);
        return  new ResponseEntity<>("user registered success",HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody LoginDto loginDto){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(),loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("user signed success",HttpStatus.OK);

    }

}

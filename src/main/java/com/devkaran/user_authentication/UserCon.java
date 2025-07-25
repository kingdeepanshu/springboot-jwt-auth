package com.devkaran.user_authentication;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCon {

    @GetMapping("/")
    public String welcome(){
        return "hello world";
    }

    @GetMapping("/auth/user")
    @PreAuthorize("hasAuthority('USER')")
    public String userDetails(){
        return "user";
    }

    @GetMapping("/auth/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminDetails(){
        return "admin";
    }
}

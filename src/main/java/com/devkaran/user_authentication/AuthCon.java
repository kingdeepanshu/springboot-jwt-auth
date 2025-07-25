package com.devkaran.user_authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthCon {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginInfo){
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginInfo.getUsername(), loginInfo.getPassword()));
        if(auth.isAuthenticated()){
            List<String> roles = auth.getAuthorities()
                    .stream()
                    .map(grantedAuthority -> grantedAuthority.getAuthority())
                    .toList();

            return jwtService.generateToken(loginInfo.getUsername(), roles);
        }else{
            throw new RuntimeException("invalid Access");
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody RegistrationDto registerDto){
        if(appUserRepo.findByUsername(registerDto.getUsername()).isPresent()){
            return "Username already exists!";
        }

        AppUser user = new AppUser();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(registerDto.getRoles()); // Set roles from input

        appUserRepo.save(user);

        return "Registration Successful!";
    }


}

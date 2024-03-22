package com.example.secyrity.controller;

import com.example.secyrity.domain.User;
import com.example.secyrity.repository.UserRepository;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/user")
public class UserController {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public void login(){}

    @GetMapping("/join")
    public void join(){}

    @PostMapping("/join")
    public String joinPro(User user){
        log.info(user);
        String password = user.getPassword();
        String enPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(enPassword);
        user.setRole("USER");
        userRepository.save(user);
        return "/";
    }
}

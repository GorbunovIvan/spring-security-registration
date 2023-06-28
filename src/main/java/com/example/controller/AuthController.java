package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        var userCreated = userService.create(user);
        if (userCreated == null) {
            return "redirect:/auth/register";
        }
        return "redirect:/";
    }

    @ModelAttribute("newUser")
    public User newUser() {
        return User.builder().build();
    }

    @ModelAttribute("currentUser")
    public String currentUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (name == null || name.equals("anonymousUser")) {
            return "";
        }
        return name;
    }
}

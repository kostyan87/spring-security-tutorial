package com.example.security.controller;

import com.example.security.dto.UserDto;
import com.example.security.entity.User;
import com.example.security.event.RegistrationCompleteEvent;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDto UserDto, final HttpServletRequest request) {
        User User = userService.registerUser(UserDto);
        publisher.publishEvent(new RegistrationCompleteEvent(
                User,
                "http://" +
                        request.getServerName() +
                        ":" +
                        request.getServerPort() +
                        request.getContextPath()
        ));
        return "Success";
    }
}

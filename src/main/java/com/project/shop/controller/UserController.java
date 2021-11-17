package com.project.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.shop.payload.UserIdentityAvailability;
import com.project.shop.payload.UserSummary;
import com.project.shop.exception.ResourceNotFoundException;
import com.project.shop.model.User;
import com.project.shop.repository.UserRepository;
import com.project.shop.security.CurrentUser;
import com.project.shop.security.UserPrincipal;

@RestController
@RequestMapping("/api")
public class UserController {
   
   @Autowired
   private UserRepository userRepository;
   
   
   private static final Logger logger = LoggerFactory.getLogger(UserController.class);

   @GetMapping("/user/me")
   @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        User user = userRepository.getOne(currentUser.getId());
      
        UserSummary userSummary = 
                new UserSummary(currentUser.getId(), currentUser.getUsername());
          return userSummary;
    }
   
    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }
    
    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }
    
    
}
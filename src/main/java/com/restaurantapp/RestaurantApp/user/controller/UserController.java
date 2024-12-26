package com.restaurantapp.RestaurantApp.user.controller;

import com.restaurantapp.RestaurantApp.user.model.User;
import com.restaurantapp.RestaurantApp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserRepository userRepository;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> addUser(@RequestBody User userBody)
    {
        Map<String, Object> errorResponse = new HashMap<>();

        if (userBody.getUsername().length() >= 5 && userBody.getPassword().length() >= 5)
        {
            userBody.setActive(true);
            userRepository.save(userBody);

            errorResponse.put("success", true);
            errorResponse.put("message", "User added successfully!");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(errorResponse);
        }
        else
        {
            errorResponse.put("success", false);
            errorResponse.put("message", "The body must have username and password and they should be at least 5 characters");

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);
        }
    }
}

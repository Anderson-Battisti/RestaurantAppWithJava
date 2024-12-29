package com.restaurantapp.RestaurantApp.user.controller;

import com.restaurantapp.RestaurantApp.user.model.User;
import com.restaurantapp.RestaurantApp.user.repository.UserRepository;
import com.restaurantapp.RestaurantApp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> addUser(@RequestBody User userBody)
    {
        System.out.println(userBody.getUsername());
        System.out.println(userBody.getPassword());
        Map<String, Object> jsonProcessed = userService.saveUser(userBody);

        if ((Boolean) jsonProcessed.get("success"))
        {
            return ResponseEntity.status(HttpStatus.OK).body(jsonProcessed);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonProcessed);
        }
    }

    @GetMapping("/getUsersList")
    public String getUsersList()
    {
        return userService.getAllUsers();
    }
}

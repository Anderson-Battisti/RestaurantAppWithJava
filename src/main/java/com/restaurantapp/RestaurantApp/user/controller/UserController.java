package com.restaurantapp.RestaurantApp.user.controller;

import com.restaurantapp.RestaurantApp.user.model.User;
import com.restaurantapp.RestaurantApp.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController
{
    @PostMapping
    @ResponseBody
    public ResponseEntity<User> addUser(@RequestBody User body)
    {
        if (!body.getUsername().isEmpty() && !body.getPassword().isEmpty())
        {
            try
            {
                User userToAdd = new User();
                //userToAdd.username = body.username;
                //userToAdd.password = body.password;
                //userToAdd.active = true;

                UserService.saveUser(userToAdd);
                return ResponseEntity.ok(userToAdd);
            }
            catch (Exception exception)
            {
                return ResponseEntity.badRequest().build();
            }
        }
        else
        {
            return ResponseEntity.badRequest().build();
        }
    }
}

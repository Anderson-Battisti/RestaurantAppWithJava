package com.restaurantapp.RestaurantApp.user.service;

import com.restaurantapp.RestaurantApp.user.model.User;

public class UserService
{
    public static void saveUser(User user)
    {
        System.out.println("User name: " + user.getUsername() + "\nPassword: " + user.getPassword());
    }
}

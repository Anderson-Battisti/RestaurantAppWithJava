package com.restaurantapp.RestaurantApp.user.repository;

import com.restaurantapp.RestaurantApp.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
}

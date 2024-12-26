package com.restaurantapp.RestaurantApp.user.repository;

import com.restaurantapp.RestaurantApp.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long>
{
}

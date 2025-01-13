package com.restaurantapp.RestaurantApp.user.repository;

import com.restaurantapp.RestaurantApp.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long>
{
    @Query( value = "SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.username = :username AND u.password = crypt( :password, u.password ) AND u.active = true" )
    boolean existsByUsernameAndPassword( String username, String password );
}

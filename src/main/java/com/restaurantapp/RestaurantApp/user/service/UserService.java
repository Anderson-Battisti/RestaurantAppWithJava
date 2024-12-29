package com.restaurantapp.RestaurantApp.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantapp.RestaurantApp.json_funcionalities.JsonFuncionalities;
import com.restaurantapp.RestaurantApp.user.repository.UserRepository;
import com.restaurantapp.RestaurantApp.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JsonFuncionalities jsonFuncionalities;

    public Map<String, Object> saveUser(User userBody)
    {
        Map<String, Object> responseJson = new HashMap<>();

        System.out.println(userBody.getUsername());
        System.out.println(userBody.getPassword());
        if (userBody.getUsername() != null && userBody.getUsername().length() >= 5 &&
            userBody.getPassword() != null && userBody.getPassword().length() >= 5)
        {
            try
            {
                userBody.setActive(true);
                User savedUser = userRepository.save(userBody);

                if (savedUser.getId() > 0)
                {
                    responseJson.put("success", true);
                    responseJson.put("message", "The user was successfully saved on database");
                    return responseJson;
                }
                else
                {
                    responseJson.put("success", false);
                    responseJson.put("message", "An error occurred while saving the user in database");
                    return responseJson;
                }
            }
            catch (DataAccessException e)
            {
                responseJson.put("success", false);
                responseJson.put("message", "Database error: " + e.getMessage());
                return responseJson;
            }
            catch (Exception e)
            {
                responseJson.put("success", false);
                responseJson.put("message", "An unexpected error occurred" + e.getMessage());
                return responseJson;
            }
        }
        else
        {
            responseJson.put("success", false);
            responseJson.put("message", "The body must have username and password and they should be at least 5 characters");
            return responseJson;
        }
    }

    public String getAllUsers()
    {
        Map<String, Object> responseJson = new HashMap<>();

        try
        {
            List<User> allUsers = userRepository.findAll();

            if (!allUsers.isEmpty())
            {
                return jsonFuncionalities.toJson(allUsers);
            }
            else
            {
                return "[]";
            }
        }
        catch (DataAccessException exception)
        {
            responseJson.put("success", false);
            responseJson.put("message", "Database error: " + exception.getMessage());
            return jsonFuncionalities.toJson(responseJson);
        }
        catch (Exception e)
        {
            responseJson.put("success", false);
            responseJson.put("message", "An unexpected error occurred" + e.getMessage());
            return jsonFuncionalities.toJson(responseJson);
        }
    }
}

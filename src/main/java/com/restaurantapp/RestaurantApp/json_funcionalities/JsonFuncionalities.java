package com.restaurantapp.RestaurantApp.json_funcionalities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonFuncionalities
{
    @Autowired
    private ObjectMapper objectMapper;

    public String toJson(Object object)
    {
        try
        {
            return objectMapper.writeValueAsString(object);
        }
        catch (JsonProcessingException jsonException)
        {
            return "{\"success\":false, \"message\":\"Error converting to JSON\"}";
        }
    }
}

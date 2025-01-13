package com.restaurantapp.RestaurantApp.user.controller;

import com.restaurantapp.RestaurantApp.user.model.User;
import com.restaurantapp.RestaurantApp.user.repository.UserRepository;
import com.restaurantapp.RestaurantApp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
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
    public ResponseEntity<Object> addUser( @RequestBody User userBody )
    {
        Map<String, Object> jsonProcessed = userService.saveUser( userBody );

        if ( (Boolean) jsonProcessed.get( "success" ) )
        {
            return ResponseEntity.status( HttpStatus.OK ).body( jsonProcessed );
        }
        else
        {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( jsonProcessed );
        }
    }

    @GetMapping( "/getUsersList" )
    public String getUsersList()
    {
        return userService.getAllUsers();
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser( @RequestBody long id )
    {
        return ""; //TO DO
    }

    @CrossOrigin( origins = "*" ) // remove the cors error
    @GetMapping( "/checkLogin" )
    public ResponseEntity<Object> checkLogin(@RequestHeader( value = HttpHeaders.AUTHORIZATION, required = false) String authorization )
    {
        Map<String, Object> response = new HashMap<>();

        if ( authorization == null || ! authorization.startsWith( "Basic " ) )
        {
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( "A autorização é inválida ou está faltando" );
        }

        try
        {
            String base64Credentials = authorization.replace( "Basic ", "" );
            String credentialsDecoded = new String( Base64.getDecoder().decode( base64Credentials ) );
            String[] credentialsDivided = credentialsDecoded.split( ":", 2);
            String username = credentialsDivided[0];
            String password = credentialsDivided[1];

            if ( credentialsDivided.length == 2 )
            {
                if ( userService.authenticatedSuccessfully( username, password ) )
                {
                    response.put( "success", true );
                    response.put( "message", "Usuário e senha batem. Login efetuado com sucesso!" );
                    return ResponseEntity.status( HttpStatus.OK ).body( response );
                }
                else
                {
                    response.put ( "error", "Falha na autenticação! O banco de dados não retornou um usuário compatível com as credenciais enviadas." );
                    return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( response );
                }
            }
            else
            {
                response.put( "error", "Falha na autenticação! O servidor não recebeu a 'Authorization' corretamente." );
                return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( response );
            }
        }
        catch ( Exception e )
        {
            response.put( "error", "Ocorreu um erro interno. Tente novamente" );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( e );
        }
    }
}

package com.pachimari.auth;

import com.pachimari.exception.InvalideException;
import com.pachimari.product.InvalidException;
import com.pachimari.product.ProductAdapter;
import com.pachimari.product.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by iPlowPlow on 10/03/2017.
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping
    public AuthDto authentification(@RequestBody String login, BindingResult bindingResult ){
        if(bindingResult.hasErrors()){
            throw new InvalideException();
        }

        AuthDto authDto = authService.getAuthByLogin(login);
        if(authDto!=null){
            return authDto;
        }
        return null;
    }

    @PostMapping("/add")
    @ResponseStatus(CREATED)
    public ResponseEntity addAuth(@RequestBody @Valid AuthDto authDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new InvalidException();
        }
        authService.createAuth(authDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{auth_id}")
                .buildAndExpand(authDto).toUri();
        return ResponseEntity.created(location).body(authDto);

    }

    @PutMapping()
    public ResponseEntity updateProduct(@RequestBody @Valid AuthDto authDto, BindingResult bindingResult){
        authService.updateAuth(authDto);
        return new ResponseEntity(authDto, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity deleteProduct(@RequestBody String login, BindingResult bindingResult){
        return new ResponseEntity(authService.deleteAuth(login),HttpStatus.OK);
    }

}

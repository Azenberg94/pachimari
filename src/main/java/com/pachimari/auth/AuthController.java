package com.pachimari.auth;

import com.pachimari.exception.InvalideException;
import com.pachimari.product.InvalidException;
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
        try{
            AuthDto authDto = authService.getAuthByLogin(login);
            return authDto;
        }catch(Exception e) {
            return null;
        }
    }

    @PostMapping("/add")
    @ResponseStatus(CREATED)
    public ResponseEntity addAuth(@RequestBody @Valid AuthDto authDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new InvalidException();
        }
        try {
            authService.createAuth(authDto);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{auth_id}")
                    .buildAndExpand(authDto).toUri();
            return ResponseEntity.created(location).body(authDto);
        }catch(Exception e ){
            return null;
        }

    }
    @GetMapping("/{auth_id}")
    public AuthDto getAuthById(@PathVariable("auth_id") String id){
        try {
            return authService.getAuthById(id);
        }catch (Exception e){
            return null;
        }
    }

    @PutMapping()
    public ResponseEntity updateProduct(@RequestBody @Valid AuthDto authDto, BindingResult bindingResult){
        try {
            authService.updateAuth(authDto);
            return new ResponseEntity(authDto, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping()
    public ResponseEntity deleteProduct(@RequestBody String login, BindingResult bindingResult){
        try {
            return new ResponseEntity(authService.deleteAuth(login), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
    }

}

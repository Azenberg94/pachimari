package com.pachimari.auth;

import com.pachimari.exception.InvalideException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by iPlowPlow on 10/03/2017.
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthRepositoryJdbc authRepositoryJdbc;

    @PostMapping
    public Integer authentification(@RequestBody @Valid AuthDto authDto, BindingResult bindingResult ){
        if(bindingResult.hasErrors()){
            throw new InvalideException();
        }
        int r = 0;
        AuthEntity authEntity  = authRepositoryJdbc.tryAuth(authDto.getLogin(), authDto.getPwd());
        if(authEntity!=null){
            r = 1;
        }
        return r;
    }

    @PostMapping("/add")
    public int addAuth(@RequestBody @Valid AuthDto authDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalideException();
        }
        authRepositoryJdbc.addAuth(authDto.getLogin(), authDto.getPwd());

        return 1;
    }

    @PostMapping("/update")
    public int updateAuth(@RequestBody @Valid AuthDto authDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalideException();
        }
        authRepositoryJdbc.updateAuth(authDto.getLogin(), authDto.getPwd());
        return 1;
    }

}

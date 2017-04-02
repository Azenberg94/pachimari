package com.pachimari.auth;

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

        return null;
    }

}

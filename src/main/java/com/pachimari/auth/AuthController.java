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
    private AuthRepository authRepository;

    @PostMapping
    public Integer authentification(@RequestBody @Valid AuthDto authDto, BindingResult bindingResult ){
        if(bindingResult.hasErrors()){
            throw new InvalideException();
        }
        int r = 0;

        AuthEntity authEntity = authRepository.findByLoginAndPwd(authDto.getLogin(), authDto.getPwd());
        if(authEntity!=null){
            r = 1;
        }
        return r;
    }

    @PostMapping("/add")
    @ResponseStatus(CREATED)
    public ResponseEntity addAuth(@RequestBody @Valid AuthDto authDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new InvalidException();
        }
        authRepository.save(AuthAdapter.toAuthEntity(authDto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{auth_id}")
                .buildAndExpand(authDto).toUri();
        return ResponseEntity.created(location).body(authDto);

    }

    @PutMapping()
    public ResponseEntity updateProduct(@RequestBody @Valid AuthDto authDto, BindingResult bindingResult){
        authRepository.save(AuthAdapter.toAuthEntity(authDto));
        return new ResponseEntity(authDto, HttpStatus.OK);
    }

}

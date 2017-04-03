package com.pachimari.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Pierre on 02/03/2017.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping()
    public List<UserDTO> getAccountList()
    {

        return userService.getList();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class BadRequestException extends RuntimeException{

    }
    @PostMapping()
    public UserDTO createAccount(@RequestBody @Valid UserDTO accountEntity, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException();

        }
      return  userService.createAccount(accountEntity);
    }
    @DeleteMapping()
    public UserDTO deleteAccount(@RequestBody  Integer id){
        return userService.deleteAccount(id);
    }
    @PutMapping()
    public UserDTO updateAccount(@RequestBody @Valid UserDTO userDTO,  BindingResult bindingResult){
        return userService.updateAccount(userDTO);
    }

}

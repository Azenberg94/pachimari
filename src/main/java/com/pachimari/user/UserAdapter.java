package com.pachimari.user;


import java.util.ArrayList;
import java.util.List;

public class UserAdapter {


    public static User toUserEntity(UserDTO userDTO){
        return User.builder()
                .login(userDTO.getLogin())
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .build();
    }

   public static UserDTO toUserDTO(User userEntity){
        return UserDTO.builder()
                .login(userEntity.getLogin())
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .build();
    }
    public static List<UserDTO> listToUserDTO(List<User> list){
       List<UserDTO> userDTOList = new ArrayList<UserDTO>();
     for(User userEntity : list){
         userDTOList.add(toUserDTO(userEntity));
     }
     return  userDTOList;
    }
}

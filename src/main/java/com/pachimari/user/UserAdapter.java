package com.pachimari.user;


import java.util.ArrayList;
import java.util.List;


public  class UserAdapter {



    public static User toUserEntity(UserDTO userDTO){
        return User.builder()
                .id(userDTO.getId())
                .login(userDTO.getLogin())
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .type(userDTO.getType())
                .lastName(userDTO.getLastName())
                .addresse(userDTO.getAddresse())
                .cp(userDTO.getCp())
                .ville(userDTO.getVille())
                .build();
    }

   public static UserDTO toUserDTO(User userEntity){
        return UserDTO.builder()
                .id(userEntity.getId())
                .login(userEntity.getLogin())
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .type(userEntity.getType())
                .lastName(userEntity.getLastName())
                .addresse(userEntity.getAddresse())
                .cp(userEntity.getCp())
                .ville(userEntity.getVille())
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

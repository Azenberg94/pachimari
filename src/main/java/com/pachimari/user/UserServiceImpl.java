package com.pachimari.user;

import com.pachimari.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Pierre on 02/03/2017.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserDTO> getList() {
        return UserAdapter.listToUserDTO(userRepository.findAll());
    }

    @Override
    public UserDTO createAccount(UserDTO userDTO) {
        User user=User.builder().id(userDTO.getId()).email(userDTO.getEmail()).login(userDTO.getLogin()).name(userDTO.getName()).build();
        userRepository.insert(user);
        return userDTO;
    }
}

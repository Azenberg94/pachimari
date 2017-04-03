package com.pachimari.user;

import java.util.List;
import java.util.Map;

/**
 * Created by Pierre on 02/03/2017.
 */
public interface UserService {
    public List<UserDTO> getList();
    public List<UserDTO> getListAdmin();

    public UserDTO createAccount(UserDTO userDTO);

    public UserDTO deleteAccount(Integer id);
    public UserDTO updateAccount(UserDTO userDTO);
    public UserDTO getUserByLogin(String login);

}

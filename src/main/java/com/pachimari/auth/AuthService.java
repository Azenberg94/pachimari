package com.pachimari.auth;

/**
 * Created by Loïc on 04/04/2017.
 */
public interface AuthService {
    public AuthDto getAuthById(String id);
    public AuthDto createAuth(AuthDto productDto);
    public AuthDto deleteAuth(String login);
    public AuthDto updateAuth(AuthDto AuthDto);
    public AuthDto getAuthByLogin(String login);


}




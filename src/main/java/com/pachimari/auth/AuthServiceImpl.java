package com.pachimari.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Loïc on 08/04/2017.
 */
@Service
public class AuthServiceImpl implements  AuthService{

    @Autowired
    AuthRepository authRepository;

    @Override
    public AuthDto createAuth(AuthDto authDto) {
        authRepository.save(AuthAdapter.toAuthEntity(authDto));
        return authDto;
    }

    @Override
    public AuthDto deleteAuth(String login) {
        AuthEntity authToDelete = authRepository.findByLogin(login);
        authRepository.delete(authToDelete);
        return AuthAdapter.toAuthDto(authToDelete);
    }

    @Override
    public AuthDto updateAuth(AuthDto AuthDto) {
        authRepository.save(AuthAdapter.toAuthEntity(AuthDto));
        return AuthDto;
    }

    @Override
    public AuthDto getAuthByLogin(String login) {
        return AuthAdapter.toAuthDto(authRepository.findByLogin(login));
    }



}

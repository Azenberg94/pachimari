package com.pachimari.auth;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Created by iPlowPlow on 10/03/2017.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {

    private Long id;
    @NotNull
    private String login;
    @NotNull
    private String pwd;

    public AuthDto(String login, String pwd){
        this.login=login;
        this.pwd=pwd;
    }

}

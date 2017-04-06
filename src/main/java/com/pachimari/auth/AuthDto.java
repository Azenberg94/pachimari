package com.pachimari.auth;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * Created by iPlowPlow on 10/03/2017.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AuthDto {

    private String id;
    @NotNull
    private String login;
    @NotNull
    private String pwd;

    public AuthDto(String login, String pwd){
        this.login=login;
    }

}

package com.pachimari.auth;

import lombok.*;

import javax.persistence.Id;

/**
 * Created by iPlowPlow on 10/03/2017.
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthEntity {

    @Id
    private String id;

    private String login;

    private String pwd;

}

package com.pachimari.auth;

import lombok.*;
import javax.persistence.*;
/**
 * Created by iPlowPlow on 10/03/2017.
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "auth")
public class AuthEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String login;

    @Column
    private String pwd;

}

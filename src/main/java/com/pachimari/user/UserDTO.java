package com.pachimari.user;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Pierre on 28/02/2017.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {


    private String id;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 40)
    private String name;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 40)
    private String lastName;
    @NotNull
    @NotBlank
    private String login;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String addresse;
    @NotNull
    @NotBlank
    private String ville;
    @NotNull
    @NotBlank
    private String cp;
    @NotNull
    @NotBlank
    private String type;
}
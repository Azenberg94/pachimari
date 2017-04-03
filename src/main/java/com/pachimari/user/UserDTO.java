package com.pachimari.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Pierre on 28/02/2017.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotNull

    private Integer id;



    @NotBlank
    @NotNull
    @Size(min = 3, max = 40)
    private String name;

    @NotNull
    @NotBlank
    private String login;

    @NotNull
    @NotBlank
    private String email;


    public Integer getId() {
            return id;
        }

    public String getName() {
        return name;
    }


    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }


}

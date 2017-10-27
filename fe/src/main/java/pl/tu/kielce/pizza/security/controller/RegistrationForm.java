package pl.tu.kielce.pizza.security.controller;


import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class RegistrationForm {

//    @Size(min=2, max=30)
    @Size(min=2, max=30, message = "{missingOrWrongValue}")
    private String userName;
    private String email;
//    private String password;
//    private String repeatPassword;

}

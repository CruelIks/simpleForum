package iks.gog.jpatst.forms;


import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {
    @NotEmpty
    private String name = "";

    @NotEmpty
    private String password = "";
}

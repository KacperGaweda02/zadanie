package org.kacper.library.user.login;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDTO {

    @NotNull
    private String login;

    @NotNull
    private String password;
}

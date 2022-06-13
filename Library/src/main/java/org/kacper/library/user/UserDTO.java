package org.kacper.library.user;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {

    private Long id;

    private LocalDate creationDate;

    private LocalDate deactivationDate;

    private String status;

    @NotNull
    private String login;

    @NotNull
    private String passwordHash;

    @NotNull
    private String role;
}

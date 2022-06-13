package org.kacper.library.user;

import lombok.RequiredArgsConstructor;
import org.kacper.library.user.role.Role;
import org.kacper.library.user.role.RoleNotFoundException;
import org.kacper.library.user.role.RoleRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkState;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final RoleRepository roleRepository;

    public User fromDTO(UserDTO userDTO) throws RoleNotFoundException {
        checkState(Objects.nonNull(userDTO));

        LocalDate creationDate = Optional.ofNullable(userDTO.getCreationDate()).orElse(LocalDate.now());
        User.Status status = Optional.ofNullable(userDTO.getStatus())
                .map(User.Status::valueOf)
                .orElse(User.Status.ACTIVE);
        Role role = roleRepository.findByName(userDTO.getRole()).orElseThrow(RoleNotFoundException::new);

        return User.builder()
                .creationDate(creationDate)
                .deactivationDate(userDTO.getDeactivationDate())
                .status(status)
                .login(userDTO.getLogin())
                .passwordHash(userDTO.getPasswordHash())
                .role(role)
                .build();
    }

    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .creationDate(user.getCreationDate())
                .deactivationDate(user.getDeactivationDate())
                .status(user.getStatus().name())
                .login(user.getLogin())
                .passwordHash(user.getPasswordHash())
                .role(user.getRole().getName())
                .build();
    }
}

package org.kacper.library.user;

import lombok.*;
import org.kacper.library.user.role.Permission;
import org.kacper.library.user.role.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkState;

@Entity
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private final Long id;

    @Column(name = "creation_date", nullable = false)
    private final LocalDate creationDate;

    @Column(name = "deactivation_date")
    private LocalDate deactivationDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "login", nullable = false)
    private final String login;

    @Column(name = "passwordHash", nullable = false)
    private final String passwordHash;

    @Column(name = "role")
    @ManyToOne(optional = false)
    private Role role;

    public enum Status {
        ACTIVE,
        DEACTIVATED
    }

    public User deactivateUser() {
        status = Status.DEACTIVATED;
        deactivationDate = LocalDate.now();
        return this;
    }

    public boolean hasPermission(Permission permissionToCheck) {
        checkState(Objects.nonNull(permissionToCheck));
        return role.getPermissions()
                .stream()
                .anyMatch(permission -> permission.equals(permissionToCheck));
    }
}

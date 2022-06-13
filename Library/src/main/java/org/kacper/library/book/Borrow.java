package org.kacper.library.book;

import lombok.*;
import org.kacper.library.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Builder
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Long id;

    @Column(name = "book")
    private final Book book;

    @Column(name = "user")
    private final User user;

    @Column(name = "creation_date")
    private final LocalDate creationDate;

    @Column(name = "expiration_date")
    private final LocalDate expirationDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        ACTIVE,
        RETURNED
    }

    public Borrow returnBook() {
        status = Status.RETURNED;
        returnDate = LocalDate.now();
        return this;
    }
}

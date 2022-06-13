package org.kacper.library.book;

import org.kacper.library.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowRepository extends CrudRepository<Borrow, Long> {
    Optional<Borrow> findByUser(User user);
}

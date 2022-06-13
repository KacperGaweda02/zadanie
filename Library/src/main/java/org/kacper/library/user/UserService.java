package org.kacper.library.user;

import lombok.RequiredArgsConstructor;
import org.kacper.library.user.login.LoginException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public User login(String login, String password) {
        return userRepository.findByLogin(login)
                .filter(user -> passwordEncoder.matches(password, user.getPasswordHash()))
                .orElseThrow(LoginException::new);
    }

    public Optional<User> find(String login) {
        return userRepository.findByLogin(login);
    }

    public User create(User toAdd) {
        return userRepository.save(toAdd);
    }

    public User remove(String login) throws UserNotFoundException {
        return userRepository.findByLogin(login)
                .map(this::remove)
                .orElseThrow(UserNotFoundException::new);
    }

    public User remove(User toRemove) {
        toRemove.deactivateUser();
        return userRepository.save(toRemove);
    }
}

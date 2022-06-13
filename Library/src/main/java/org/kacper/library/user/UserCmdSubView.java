package org.kacper.library.user;

import lombok.RequiredArgsConstructor;
import org.kacper.library.user.login.LoginDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class UserCmdSubView {

    public Scanner scanner = new Scanner(System.in);
    private final PasswordEncoder passwordEncoder;
    private final UserController userController;

    public UserDTO login() {
        System.out.println("login:");
        String login = scanner.next();
        System.out.println("password:");
        String passwd = scanner.next();
        LoginDTO loginDTO = LoginDTO.builder()
                .login(login)
                .password(passwd)
                .build();
        return userController.validate(loginDTO)
                .getBody();

    }

    public void add() {
        System.out.println("What's the login?");
        String userLogin = scanner.nextLine();
        System.out.println("Who is the password?");
        String userPassword = scanner.nextLine();
        System.out.println("Do you want this user to be admin (write 1 if you'd like to)?");
        boolean isAdmin = Boolean.parseBoolean(scanner.nextLine());

        String role = isAdmin ? "admin" : "user"; // hardcoded values, I do not like this, but eh... CMD apps...
        UserDTO newUser = UserDTO.builder()
                .creationDate(LocalDate.now())
                .login(userLogin)
                .passwordHash(passwordEncoder.encode(userPassword))
                .role(role)
                .build();
        userController.create(newUser);
        System.out.println("User created!");
    }

    public void remove() {
        System.out.println("Which user would you like to remove?");
        String userLogin = scanner.nextLine();
        System.out.println("Are you sure? (Y/N)");
        String confirmation = scanner.nextLine();
        if (confirmation.equals("Y")) {
            System.out.println("Removing user " + userLogin);
            userController.delete(userLogin);
        } else {
            System.out.println("Cancelling...");
        }
    }
}

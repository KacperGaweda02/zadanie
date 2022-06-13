package org.kacper.library.book;

import lombok.RequiredArgsConstructor;
import org.kacper.library.user.UserRepository;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
@RequiredArgsConstructor
public class BookController {

    private BookService bookService;
    private BookView view;

    public Scanner scanner = new Scanner(System.in);
    public String input;
    public int bookToRemove;
    public int bookToSee;
    public String login;
    public String passwd;
    public String userToRemove;
    public String blankValue;
    public int bookToBorrow;
    public int bookToGiveBack;
    boolean isWorking = true;


}

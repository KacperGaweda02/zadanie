package org.kacper.library.book;

import lombok.RequiredArgsConstructor;
import org.kacper.library.user.UserController;
import org.kacper.library.user.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class BookView {
    public String newTitle;
    public String newAuthor;
    public String newGenre;
    public String newDescrip;



    public void showMenuForUser() {
        System.out.println("1 - see what books do we have,");
        System.out.println("2 - view an info about particular book,");
        System.out.println("3 - search for specific book,");
        System.out.println("4 - borrow a book,");
        System.out.println("5 - give book back,");
        System.out.println("6 - quit.");
    }

    public void showMenuForAdmin() {
        System.out.println("1 - add a book,");
        System.out.println("2 - remove a book,");
        System.out.println("3 - add an user,");
        System.out.println("4 - remove an user,");
        System.out.println("5 - borrow a book,");
        System.out.println("6 - give book back,");
        System.out.println("7 - quit.");
    }

    public void askAboutBookRemoval() {
        System.out.println("Which book would you like to remove from library (write an id)?");
    }

    public void askAboutSpecBook() {
        System.out.println("About which book would you like to see info (write it's id)?");
    }

    public void addBook() {
        System.out.println("What's the title?");
        newTitle = scanner.nextLine();
        System.out.println("Who is the author?");
        newAuthor = scanner.nextLine();
        System.out.println("What's the genre?");
        newGenre = scanner.nextLine();
        System.out.println("What's the description?");
        newDescrip = scanner.nextLine();
        BookRepository.addBook(newTitle, newAuthor, newGenre, newDescrip);
        showMenuForAdmin();
        BookController.askAdminAndGive();
    }

    public void searchingInstructions() {
        System.out.println("Write text that the title has in and press enter.");
    }



    public void askWhichBookToBorrow() {
        System.out.println("Which book would you like to borrow (write it's id)?");
    }

    public void askWhichBookToGiveBack() {
        System.out.println("Which book would you like to give back (write it's id)?");
    }
}

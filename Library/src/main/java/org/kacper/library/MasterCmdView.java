package org.kacper.library;

import lombok.RequiredArgsConstructor;
import org.kacper.library.book.BookView;
import org.kacper.library.user.UserCmdSubView;
import org.kacper.library.user.UserDTO;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class MasterCmdView {

    public Scanner scanner = new Scanner(System.in);
    private final UserCmdSubView userCmdSubView;

    public void start() throws InterruptedException {
        UserDTO user = userCmdSubView.login(); // throws runtime exception when failed
        greeting();

        if (user.getRole().equals("admin")) {
            adminService();
        } else {
            userService();
        }
    }

    public void greeting() {
        System.out.println("Welcome in our library, choose a thing you'd like to do (write a number):");
    }

    public void userService() throws InterruptedException {
        boolean isWorking = true;
        while (isWorking) {
            showMenuForUser();
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> bookCmdSubView.showTitles();
                case "2" -> bookCmdSubView.viewInfo();
                case "3" -> bookCmdSubView.searchBook();
                case "4" -> borrowCmdSubView.borrowBookUser();
                case "5" -> borrowCmdSubView.giveBookBackUser();
                case "6" -> isWorking = false;
                default -> {
                    System.out.println("Use proper number:");
                    askUserAndGive();
                }
            }
        }
    }

    public void showMenuForUser() {
        System.out.println("1 - see what books do we have,");
        System.out.println("2 - view an info about particular book,");
        System.out.println("3 - search for specific book,");
        System.out.println("4 - borrow a book,");
        System.out.println("5 - give book back,");
        System.out.println("6 - quit.");
    }

    public void adminService() {
        boolean isWorking = true;
        while (isWorking) {
            showMenuForAdmin();
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> bookCmdSubView.addBook();
                case "2" -> bookCmdSubView.bookRemoval();
                case "3" -> userCmdSubView.add();
                case "4" -> userCmdSubView.remove();
                case "5" -> borrowCmdSubView.borrowBookAdmin();
                case "6" -> borrowCmdSubView.giveBookBackAdmin();
                case "7" -> isWorking = false;
                default -> {
                    System.out.println("Use proper number:");
                    askAdminAndGive();
                }
            }
        }
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

    /*
     * REFACTOR END. FOR NOW
     */
    public void getBookToRemove() {
        bookToRemove = scanner.nextInt();
        BookRepository.removeBook(bookToRemove);
        BookView.showMenuForAdmin();
        askAdminAndGive();
    }

    public void getSpecBook() {
        bookToSee = scanner.nextInt();
        BookRepository.selectSpecBook(bookToSee);
        BookView.showMenuForUser();
        askUserAndGive();
    }


    public void logAdmin() {
        BookRepository.informAboutMissingBooks();
        BookView.greeting();
        BookView.showMenuForAdmin();
        askAdminAndGive();
    }

    public void logUser() {
        BookView.greeting();
        BookView.showMenuForUser();
        askUserAndGive();
    }

    public void askUserAndGive() {
        input = scanner.next();
        userService(input);
    }

    public void askAdminAndGive() {
        input = scanner.next();
        adminService(input);
    }

    public void removeUser() {
        BookView.askAboutUserRemoval();
        UserRepository.selectUsers();
        blankValue = scanner.nextLine();
        userToRemove = scanner.nextLine();
        UserRepository.validateUserRemoval(userToRemove);
        BookView.showMenuForAdmin();
        askAdminAndGive();
    }

    public void borrowBookAdmin() {
        BookView.askWhichBookToBorrow();
        BookRepository.selectAvailableBooks();
        bookToBorrow = scanner.nextInt();
        BookRepository.borrowBook(bookToBorrow);
        BookView.showMenuForAdmin();
        askAdminAndGive();
    }

    public void borrowBookUser() {
        BookView.askWhichBookToBorrow();
        BookRepository.selectAvailableBooks();
        bookToBorrow = scanner.nextInt();
        BookRepository.borrowBook(bookToBorrow);
        BookView.showMenuForUser();
        askUserAndGive();
    }

    public void giveBookBackAdmin() {
        BookView.askWhichBookToGiveBack();
        BookRepository.selectBorrowedBooks();
        bookToGiveBack = scanner.nextInt();
        BookRepository.giveBookBack(bookToGiveBack);
        BookView.showMenuForAdmin();
        askAdminAndGive();
    }

    public void giveBookBackUser() {
        BookView.askWhichBookToGiveBack();
        BookRepository.selectBorrowedBooks();
        bookToGiveBack = scanner.nextInt();
        BookRepository.giveBookBack(bookToGiveBack);
        BookView.showMenuForUser();
        askUserAndGive();
    }
}

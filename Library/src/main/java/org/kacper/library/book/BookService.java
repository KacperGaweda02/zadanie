package org.kacper.library.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    public void showTitles() {
        BookRepository.selectBooks();
        BookView.showMenuForUser();
        BookController.askUserAndGive();
    }

    public void bookRemoval() {
        BookView.askAboutBookRemoval();
        BookRepository.selectBooksAndId();
        BookController.getBookToRemove();
    }

    public void viewInfo() {
        BookView.askAboutSpecBook();
        BookRepository.selectBooksAndId();
        BookController.getSpecBook();
    }

    //public static void saveInFile() {
    //    BookRepository.saveFile();
    //    BookView.showMenu();
    //    BookController.askAndGive();
    //}
    public static void searchBook() {
        BookView.searchingInstructions();
        bookToSearch = scanner.nextLine();
        BookRepository.searchBook(bookToSearch);
        BookView.showMenuForUser();
        BookController.askUserAndGive();
    }
}

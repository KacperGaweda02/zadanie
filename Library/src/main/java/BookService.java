import java.util.Scanner;

public class BookService {
    public static Scanner scanner = new Scanner(System.in);
    public static String bookToSearch;
    public static void showTitles() {
        BookRepository.selectBooks();
        BookView.showMenu();
        BookController.askAndGive();
    }

    public static void bookRemoval() {
        BookView.askAboutBookRemoval();
        BookRepository.selectBooksAndId();
        BookController.getBookToRemove();
    }
    public static void viewInfo() {
        BookView.askAboutSpecBook();
        BookRepository.selectBooksAndId();
        BookController.getSpecBook();
    }
    public static void saveInFile() {
        BookRepository.saveFile();
        BookView.showMenu();
        BookController.askAndGive();
    }
    public static void searchBook() {
        BookView.searchingInstructions();
        bookToSearch = scanner.nextLine();
        BookRepository.searchBook(bookToSearch);
        BookView.showMenu();
        BookController.askAndGive();
    }
}

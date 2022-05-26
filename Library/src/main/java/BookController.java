import java.util.Scanner;
public class BookController {
    public static Scanner scanner = new Scanner(System.in);
    public static String input;
    public static int bookToRemove;
    public static int bookToSee;
    static boolean isWorking = true;
    public static void doSomethingWithIt(String input) {
        while (isWorking) {
            switch (input) {
                case "1":
                    input = "0";
                    BookService.showTitles();
                    break;
                case "2":
                    input = "0";
                    BookView.addBook();
                    break;
                case "3":
                    input = "0";
                    BookService.bookRemoval();
                    break;
                case "4":
                    input = "0";
                    BookService.viewInfo();
                    break;
                case "5":
                    input = "0";
                    BookService.saveInFile();
                    break;
                case "6":
                    input = "0";
                    BookService.searchBook();
                    break;
                case "7":
                    input = "0";
                    isWorking = false;
                    break;
                default:
                    input = "0";
                    BookView.getMad();
                    break;
            }
        }
    }
    public static void letsGetStarted() {
        BookView.greeting();
        BookView.showMenu();
        askAndGive();
    }
    public static void askAndGive() {
        input = scanner.next();
        doSomethingWithIt(input);
    }
    public static void getBookToRemove() {
        bookToRemove = scanner.nextInt();
        BookRepository.removeBook(bookToRemove);
        BookView.showMenu();
        askAndGive();
    }
    public static void getSpecBook() {
        bookToSee = scanner.nextInt();
        BookRepository.selectSpecBook(bookToSee);
        BookView.showMenu();
        askAndGive();
    }
}

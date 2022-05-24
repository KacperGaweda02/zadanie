import java.util.Scanner;
public class BookController {
    public static Scanner scanner = new Scanner(System.in);
    public static String input;
    public static String bookToRemove;
    public static String bookToSee;
    public static String blankValue;
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
        BookController.askAndGive();
    }
    public static void askAndGive() {
        input = scanner.next();
        BookController.doSomethingWithIt(input);
    }
    public static void getBookToRemove() {
        blankValue = scanner.nextLine();
        bookToRemove = scanner.nextLine();
        try {
            BookService.removeBook(bookToRemove);
        } catch (BookNotFoundException e) {
            BookView.noSuchABook();
        }
        BookView.showMenu();
        BookController.askAndGive();
    }
    public static void getSpecBook() {
        blankValue = scanner.nextLine();
        bookToSee = scanner.nextLine();
        BookService.showInfo(bookToSee);
        BookView.showMenu();
        BookController.askAndGive();
    }
}

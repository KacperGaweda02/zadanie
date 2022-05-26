import java.util.Scanner;

public class BookView {
    static Book output;
    public static String newTitle;
    public static String newAuthor;
    public static String newGenre;
    public static String newDescrip;
    public static Scanner scanner = new Scanner(System.in);
    public static void greeting() {
        System.out.println("Welcome in our library, choose a thing you'd like to do (write a number):");
    }
    public static void showMenu() {
        System.out.println("1 - see what books do we have,");
        System.out.println("2 - add a book,");
        System.out.println("3 - remove a book,");
        System.out.println("4 - view an info about particular book,");
        System.out.println("5 - save what books do we have in a file,");
        System.out.println("6 - search for specific book,");
        System.out.println("7 - quit.");
    }
    public static void askAboutBookRemoval() {
        System.out.println("Which book would you like to remove from library (write an id)?");
    }
    public static void askAboutSpecBook() {
        System.out.println("About which book would you like to see info (write it's id)?");
    }
    public static void getMad() {
        System.out.println("Use proper number:");
        showMenu();
        BookController.askAndGive();
    }
    public static void addBook() {
        System.out.println("What's the title?");
        newTitle = scanner.nextLine();
        System.out.println("Who is the author?");
        newAuthor = scanner.nextLine();
        System.out.println("What's the genre?");
        newGenre = scanner.nextLine();
        System.out.println("What's the description?");
        newDescrip = scanner.nextLine();
        BookRepository.addBook(newTitle, newAuthor, newGenre, newDescrip);
        showMenu();
        BookController.askAndGive();
    }
    public static void searchingInstructions() {
        System.out.println("Write text that the title has in and press enter.");
    }
}

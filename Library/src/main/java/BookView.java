import java.util.Scanner;

public class BookView {
    public static String newTitle;
    public static String newAuthor;
    public static String newGenre;
    public static String newDescrip;
    public static Scanner scanner = new Scanner(System.in);
    public static String newLogin;
    public static String newPasswd;
    public static String isAdmin;
    public static void greeting() {
        System.out.println("Welcome in our library, choose a thing you'd like to do (write a number):");
    }
    public static void showMenuForUser() {
        System.out.println("1 - see what books do we have,");
        System.out.println("2 - view an info about particular book,");
        System.out.println("3 - search for specific book,");
        System.out.println("4 - quit.");
    }
    public static void showMenuForAdmin() {
        System.out.println("5 - add a book,");
        System.out.println("6 - remove a book,");
        System.out.println("7 - add an user,");
        System.out.println("8 - remove an user,");
        System.out.println("4 - quit.");
    }
    public static void askAboutBookRemoval() {
        System.out.println("Which book would you like to remove from library (write an id)?");
    }
    public static void askAboutSpecBook() {
        System.out.println("About which book would you like to see info (write it's id)?");
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
        showMenuForAdmin();
        BookController.askAdminAndGive();
    }
    public static void searchingInstructions() {
        System.out.println("Write text that the title has in and press enter.");
    }
    public static void addUser() {
        System.out.println("What's the login?");
        newLogin = scanner.nextLine();
        System.out.println("Who is the password?");
        newPasswd = scanner.nextLine();
        System.out.println("Do you want this user to be admin (write 1 if you'd like to)?");
        isAdmin = scanner.nextLine();
        UserRepository.validateNewUser(newLogin, newPasswd, isAdmin);
        showMenuForAdmin();
        BookController.askAdminAndGive();
    }
    public static void askAboutUserRemoval() {
        System.out.println("Which user would you like to remove?");
    }
}

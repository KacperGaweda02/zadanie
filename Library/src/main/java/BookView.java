import java.util.Scanner;

public class BookView {
    static Book output;
    public static String newTitle;
    public static String newAuthor;
    public static String newGenre;
    public static String newDescrip;
    public static String blankValue;
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
        System.out.println("6 - quit.");
    }
    public static void printTitles(int i) {
        output = BookRepository.listOfBooks.get(i);
        System.out.println(output.title);
    }
    public static void askAboutBookRemoval() {
        System.out.println("Which book would you like to remove from library (write a name)?");
    }
    public static void noSuchABook() {
        System.out.println("We don't have such a book!");
    }
    public static void askAboutSpecBook() {
        System.out.println("About which book would you like to see info (write it's name)?");
    }
    public static void showDetails(String title, String author, String genre, String descrip) {
        System.out.println("title:" + title + "\nauthor:" + author + "\ngenre:" + genre + "\ndescrip:" + descrip);
    }
    public static void getMad() {
        System.out.println("Use proper number:");
        showMenu();
        BookController.askAndGive();
    }
    public static void addBook() {
        System.out.println("What's the title?");
        blankValue = scanner.nextLine();
        newTitle = scanner.nextLine();
        System.out.println("Who is the author?");
        newAuthor = scanner.nextLine();
        System.out.println("What's the genre?");
        newGenre = scanner.nextLine();
        System.out.println("What's the description?");
        newDescrip = scanner.nextLine();
        BookService.createNewBook(newTitle, newAuthor, newGenre, newDescrip);
        showMenu();
        BookController.askAndGive();
    }
}

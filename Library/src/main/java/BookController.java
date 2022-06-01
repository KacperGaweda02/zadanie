import java.util.Scanner;
public class BookController {
    public static Scanner scanner = new Scanner(System.in);
    public static String input;
    public static int bookToRemove;
    public static int bookToSee;
    static boolean isWorking = true;
    public static String login;
    public static String passwd;
    public static String userToRemove;
    public static void userService(String input) {
        while (isWorking) {
            switch (input) {
                case "1":
                    input = "0";
                    BookService.showTitles();
                    break;
                case "2":
                    input = "0";
                    BookService.viewInfo();
                    break;
                case "3":
                    input = "0";
                    BookService.searchBook();
                    break;
                case "4":
                    input = "0";
                    isWorking = false;
                    break;
                default:
                    input = "0";
                    System.out.println("Use proper number:");
                    askUserAndGive();
                    break;
            }
        }
    }
    public static void adminService(String input) {
        while (isWorking) {
            switch (input) {
                case "4":
                    input = "0";
                    isWorking = false;
                    break;
                case "5":
                    input = "0";
                    BookView.addBook();
                    break;
                case "6":
                    input = "0";
                    BookService.bookRemoval();
                    break;
                case "7":
                    input = "0";
                    BookView.addUser();
                    break;
                case "8":
                    input = "0";
                    removeUser();
                    break;
                default:
                    input = "0";
                    System.out.println("Use proper number:");
                    askAdminAndGive();
                    break;
                //case "5":
                //    input = "0";
                //    BookService.saveInFile();
                //    break;
            }
        }
    }
    public static void getBookToRemove() {
        bookToRemove = scanner.nextInt();
        BookRepository.removeBook(bookToRemove);
        BookView.showMenuForAdmin();
        askAdminAndGive();
    }
    public static void getSpecBook() {
        bookToSee = scanner.nextInt();
        BookRepository.selectSpecBook(bookToSee);
        BookView.showMenuForUser();
        askUserAndGive();
    }
    public static void logging() {
        System.out.println("login:");
        login = scanner.next();
        System.out.println("password:");
        passwd = scanner.next();
        UserRepository.validate(login, passwd);
    }
    public static void logAdmin() {
        BookView.greeting();
        BookView.showMenuForAdmin();
        askAdminAndGive();
    }
    public static void logUser() {
        BookView.greeting();
        BookView.showMenuForUser();
        askUserAndGive();
    }
    public static void askUserAndGive() {
        input = scanner.next();
        userService(input);
    }
    public static void askAdminAndGive() {
        input = scanner.next();
        adminService(input);
    }
    public static void removeUser() {
        BookView.askAboutUserRemoval();
        UserRepository.selectUsers();
        userToRemove = scanner.next();
        UserRepository.validateUserRemoval(userToRemove);
        BookView.showMenuForAdmin();
        askAdminAndGive();
    }
}

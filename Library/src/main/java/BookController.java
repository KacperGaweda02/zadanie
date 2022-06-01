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
    public static String blankValue;
    public static int bookToBorrow;
    public static int bookToGiveBack;
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
                    borrowBookUser();
                    break;
                case "5":
                    input = "0";
                    giveBookBackUser();
                    break;
                case "6":
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
                case "1":
                    input = "0";
                    BookView.addBook();
                    break;
                case "2":
                    input = "0";
                    BookService.bookRemoval();
                    break;
                case "3":
                    input = "0";
                    BookView.addUser();
                    break;
                case "4":
                    input = "0";
                    removeUser();
                    break;
                case "5":
                    input = "0";
                    borrowBookAdmin();
                    break;
                case "6":
                    input = "0";
                    giveBookBackAdmin();
                    break;
                case "7":
                    input = "0";
                    isWorking = false;
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
        blankValue = scanner.nextLine();
        userToRemove = scanner.nextLine();
        UserRepository.validateUserRemoval(userToRemove);
        BookView.showMenuForAdmin();
        askAdminAndGive();
    }
    public static void borrowBookAdmin() {
        BookView.askWhichBookToBorrow();
        BookRepository.selectAvailableBooks();
        bookToBorrow = scanner.nextInt();
        BookRepository.borrowBook(bookToBorrow);
        BookView.showMenuForAdmin();
        askAdminAndGive();
    }
    public static void borrowBookUser() {
        BookView.askWhichBookToBorrow();
        BookRepository.selectAvailableBooks();
        bookToBorrow = scanner.nextInt();
        BookRepository.borrowBook(bookToBorrow);
        BookView.showMenuForUser();
        askUserAndGive();
    }
    public static void giveBookBackAdmin() {
        BookView.askWhichBookToGiveBack();
        BookRepository.selectBorrowedBooks();
        bookToGiveBack = scanner.nextInt();
        BookRepository.giveBookBack(bookToGiveBack);
        BookView.showMenuForAdmin();
        askAdminAndGive();
    }
    public static void giveBookBackUser() {
        BookView.askWhichBookToGiveBack();
        BookRepository.selectBorrowedBooks();
        bookToGiveBack = scanner.nextInt();
        BookRepository.giveBookBack(bookToGiveBack);
        BookView.showMenuForUser();
        askUserAndGive();
    }
}

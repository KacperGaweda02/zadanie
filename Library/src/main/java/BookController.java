import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping(path="/library")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
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
        askAndGive();
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
    @PostMapping(path="/add")
    public @ResponseBody String addNewBook(@RequestParam String title, @RequestParam String author, @RequestParam String genre, @RequestParam String descrip) {

        Book n = new Book();
        n.setTitle(title);
        n.setAuthor(author);
        n.setGenre(genre);
        n.setDescrip(descrip);
        BookRepository.save(n);
        return "Saved";
    }
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Book> getAllBooks() {
        return BookRepository.findAll();
    }
}

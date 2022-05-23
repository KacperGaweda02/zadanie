import java.util.Optional;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping(path="/library")
public class BookController {
    static BookView bookView = new BookView();
    @Autowired
    public BookRepository bookRepository = new BookRepository() {
        @Override
        public <S extends Book> S save(S entity) {
            return null;
        }

        @Override
        public <S extends Book> Iterable<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public Optional<Book> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public Iterable<Book> findAll() {
            return null;
        }

        @Override
        public Iterable<Book> findAllById(Iterable<Integer> integers) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

        }

        @Override
        public void delete(Book entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Integer> integers) {

        }

        @Override
        public void deleteAll(Iterable<? extends Book> entities) {

        }

        @Override
        public void deleteAll() {

        }
    };
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
                    //BookService.showTitles();
                    break;
                case "2":
                    input = "0";
                    bookView.addBook();
                    break;
                case "3":
                    input = "0";
                    //BookService.bookRemoval();
                    break;
                case "4":
                    input = "0";
                    //BookService.viewInfo();
                    break;
                case "5":
                    input = "0";
                    //BookService.saveInFile();
                    break;
                case "6":
                    input = "0";
                    isWorking = false;
                    break;
                default:
                    input = "0";
                    //BookView.getMad();
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
    /*
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
    }*/
    @PostMapping(path="/add")
    public @ResponseBody String addNewBook(@RequestParam String newTitle, @RequestParam String newAuthor, @RequestParam String newGenre, @RequestParam String newDescrip) {
        Book n = new Book();
        n.setTitle(newTitle);
        n.setAuthor(newAuthor);
        n.setGenre(newGenre);
        n.setDescrip(newDescrip);
        bookRepository.save(n);
        return "Saved";
    }
    /*
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Book> getAllBooks() {
        return BookRepository.findAll();
    }*/
}

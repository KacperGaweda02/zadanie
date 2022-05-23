import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.google.common.collect.MoreCollectors;
public class BookService {
    public static void showTitles() {
        for (int i = 0; i<BookRepository.listOfBooks.size(); i++) {
            BookView.printTitles(i);
        }
        BookView.showMenu();
        BookController.askAndGive();
    }
    public static void createNewBook(String title, String author, String genre, String descrip) {
        BookRepository.listOfBooks.add(new Book(title, author, genre, descrip));
    }
    public static void bookRemoval() {
        BookView.askAboutBookRemoval();
        for (int i = 0; i < BookRepository.listOfBooks.size(); i++) {
            BookView.printTitles(i);
        }
        BookController.getBookToRemove();
    }
    public static void removeBook(String bookName) throws BookNotFoundException {
        ArrayList<Book> books = BookRepository.listOfBooks;
        Book toRemove = books.stream()
                .filter(book -> bookName.equals(book.title))
                .collect(MoreCollectors.toOptional())
                .orElseThrow(BookNotFoundException::new);

        BookRepository.listOfBooks.remove(toRemove);
        BookView.showMenu();
        BookController.askAndGive();
    }
    public static void viewInfo() {
        BookView.askAboutSpecBook();
        for (int i = 0; i<BookRepository.listOfBooks.size(); i++) {
            BookView.printTitles(i);
        }
        BookController.getSpecBook();
    }
    public static void showInfo(String bookToSee) {
        int isExisting = 0;
        for (int i = 0; i<BookRepository.listOfBooks.size(); i++) {
            if (BookRepository.listOfBooks.get(i).title.equals(bookToSee)) {
                BookView.showDetails(BookRepository.listOfBooks.get(i).title, BookRepository.listOfBooks.get(i).author, BookRepository.listOfBooks.get(i).genre, BookRepository.listOfBooks.get(i).descrip);
                isExisting++;
            }
        }
        if (isExisting==0) {
            BookView.noSuchABook();
        }
    }
    public static void saveInFile() {
        try {
            FileWriter writer = new FileWriter("books_in_library.txt");
            writer.write("title:" + BookRepository.listOfBooks.get(0).title + " author:" + BookRepository.listOfBooks.get(0).author + " genre:" + BookRepository.listOfBooks.get(0).genre + " description:" + BookRepository.listOfBooks.get(0).descrip + "\n");
            for (int i=1; i<BookRepository.listOfBooks.size(); i++) {
                writer.append("title:").append(BookRepository.listOfBooks.get(i).title).append(" author:").append(BookRepository.listOfBooks.get(i).author).append(" genre:").append(BookRepository.listOfBooks.get(i).genre).append(" description:").append(BookRepository.listOfBooks.get(i).descrip).append("\n");
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        BookView.showMenu();
        BookController.askAndGive();
    }
}

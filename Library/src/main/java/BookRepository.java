import java.util.ArrayList;

class BookRepository {
    public static ArrayList<Book> listOfBooks = new ArrayList<>();
    public static void createSomeBooks() {
        listOfBooks.add(new Book("book1", "author1", "genre1", "description1"));
        listOfBooks.add(new Book("book2", "author2", "genre2", "description2"));
        listOfBooks.add(new Book("book3", "author3", "genre3", "description3"));
    }
}

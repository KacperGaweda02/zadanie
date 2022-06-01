//import java.io.IOException;
import java.sql.*;
//import java.io.FileWriter;
class BookRepository {
    static final String DB_URL = "jdbc:mysql://localhost/db";
    static final String USER = "root";
    static final String PASS = "start123";
    static final String QUERY0 = "SELECT * FROM books";
    public static void selectBooks() {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY0);) {
            while (rs.next()) {
                System.out.print(rs.getString("title") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void selectBooksAndId() {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY0);) {
            while (rs.next()) {
                System.out.print("id:" + rs.getInt("id") + " ");
                System.out.println("title:" + rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addBook(String title, String author, String genre, String descrip) {
        try{Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pst = conn.prepareStatement("INSERT INTO books VALUES (NULL, ?, ?, ?, ?, 'yes', 'nobody')");
            pst.setString(1,title);
            pst.setString(2,author);
            pst.setString(3,genre);
            pst.setString(4,descrip);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void removeBook(int id) {
        int permissionToDeleteBook=1;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books WHERE id=" + id + " AND isAvailable='no'");) {
            if (rs.next()) {
                System.out.println("You can't delete book, because it is borrowed by someone.");
                --permissionToDeleteBook;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(permissionToDeleteBook==1) {
            try {
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement pst = conn.prepareStatement("DELETE FROM books WHERE id=?");
                pst.setInt(1, id);
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void selectSpecBook(int id) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books WHERE id=" + id);
        ) {
            while(rs.next()){
                System.out.println("id:" + rs.getInt("id"));
                System.out.println("title:" + rs.getString("title"));
                System.out.println("author:" + rs.getString("author"));
                System.out.println("genre:" + rs.getString("genre"));
                System.out.println("description:" + rs.getString("description"));
                System.out.println("available:" + rs.getString("isAvailable"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //public static void saveFile() {
    //  try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    //    FileWriter writer = new FileWriter("books_in_library.txt");
    //  Statement stmt = conn.createStatement();
    //ResultSet rs = stmt.executeQuery(QUERY0);
    //    ) {
    //        while(rs.next()){
    //            writer.write("id:" + rs.getInt("id") + ", title:" + rs.getString("title") + ", author:" + rs.getString("author") + ", genre:" + rs.getString("genre") + ", description:" + rs.getString("description") + "\n");
    //        }
    //    } catch (SQLException e){
    //        e.printStackTrace();
    //    } catch (IOException e){
    //        e.printStackTrace();
    //    }
    //}
    public static void searchBook(String string) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books WHERE title LIKE '%" + string + "%'");
        ) {
            while(rs.next()){
                System.out.print("id:" + rs.getInt("id"));
                System.out.println(", title:" + rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void selectAvailableBooks() {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books WHERE isAvailable='yes'");
        ) {
            while(rs.next()){
                System.out.print("id:" + rs.getInt("id"));
                System.out.println(", title:" + rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void borrowBook(int id) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();){
            stmt.executeUpdate("UPDATE books SET isAvailable='no' WHERE id=" + id);
            stmt.executeUpdate("UPDATE books SET borrowedBy='" + UserRepository.currentUser.login + "' WHERE id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void selectBorrowedBooks() {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books WHERE borrowedBy='" + UserRepository.currentUser.login + "'");
        ) {
            while(rs.next()){
                System.out.print("id:" + rs.getInt("id"));
                System.out.println(", title:" + rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void giveBookBack(int id) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();){
            stmt.executeUpdate("UPDATE books SET isAvailable='yes' WHERE id=" + id);
            stmt.executeUpdate("UPDATE books SET borrowedBy='nobody' WHERE id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

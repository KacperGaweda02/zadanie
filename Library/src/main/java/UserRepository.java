import java.sql.*;

public class UserRepository {
    static final String DB_URL = "jdbc:mysql://localhost/db";
    static final String USER = "root";
    static final String PASS = "start123";
    static boolean admin;
    public static User currentUser;
    public static void validate(String login, String passwd) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE login='" + login + "' AND passwd='" + passwd + "';");
        ) {
            if(rs.next()) {
                if(rs.getBoolean("isAdmin")) {
                    currentUser = new User(login);
                    BookController.logAdmin();
                }
                else {
                    currentUser = new User(login);
                    BookController.logUser();
                }
            }
            else {
                System.out.println("Bad data, try again.");
                BookController.logging();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void validateNewUser(String login, String passwd, String isAdmin) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE login='" + login + "'");
        ) {
            if(rs.next()) {
                System.out.println("There is already such a user, try again with unique login.");
            }
            else {
                createUser(login, passwd, isAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createUser(String login, String passwd, String isAdmin) {
        if (isAdmin.equals("1")) {
            admin = true;
        }
        else {
            admin = false;
        }
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();){
            stmt.executeUpdate("INSERT INTO users VALUES ('" + login + "', '" + passwd + "', " + admin + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void selectUsers() {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");) {
            while (rs.next()) {
                System.out.println(rs.getString("login"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void validateUserRemoval(String userToRemove) {
        int permissionToRemoveUser = 1;
        if (userToRemove.equals("admin")) {
            System.out.println("You can't delete this account - this is default admin!");
            --permissionToRemoveUser;
        }else if (userToRemove.equals(currentUser.login)) {
            System.out.println("You can't delete account that you're logged in - try on other admin account.");
            --permissionToRemoveUser;
        }
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books WHERE borrowedBy='" + userToRemove + "'");) {
            if (rs.next()) {
                System.out.println("You can't delete this user, because this person still has some books.");
                --permissionToRemoveUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (permissionToRemoveUser==1) {
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE login='" + userToRemove + "';");) {
                if (rs.next()) {
                    deleteUser(userToRemove);
                } else {
                    System.out.println("There is no such a user.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void deleteUser(String userToRemove) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();){
            stmt.executeUpdate("DELETE FROM users WHERE login='" + userToRemove + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

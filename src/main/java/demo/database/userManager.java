package demo.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DanielY on 6/24/2015.
 */
public class userManager extends DbManager {
    private userManager(){}

    /**
     * Hashes the provided password using SHA-1 encryption Algo
     * @param password The password to be hashed
     * @return String representing the hashed password.
     */
    private static String hashPassword(String password) {
        return password;
    }

    /**
     * Adds the specified user information to the database.
     * @param email New user's email address
     * @param firstName New user's first name
     * @param lastName New user's last name
     * @param password New user's password
     * @return void
     * @throws SQLException
     */
    public static void addUser(String email, String firstName, String lastName, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        connection = getConnection(false);
        String query = "INSERT INTO users (Username, FirstName, LastName, Password) VALUES (?, ?, ?, ?);";
        statement = connection.prepareStatement(query);
        statement.setString(1, email);
        statement.setString(2, firstName);
        statement.setString(3, lastName);
        statement.setString(4, hashPassword(password));
        statement.execute();
        connection.close();
    }

    /**
     * Returns true if the user email and password are valid, false otherwise.
     * @param email user's email
     * @param password user's password
     * @return if given email matches given password
     * @throws SQLException
     */
    public static boolean userIsValid(String email, String password) throws SQLException {
        boolean isValid = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet results;

        connection = getConnection(true);
        String query = "SELECT Password FROM users WHERE Username='" + email + "'";
        statement = connection.prepareStatement(query);
        results = statement.executeQuery();
        if(results.next()){
            if(hashPassword(password).equals(results.getString("Password")))
                isValid = true;
        }
        connection.close();
        return isValid;
    }

    /**
     * Temporary method.
     * @param email User's registered email address
     * @return The password mapped to the given email address
     * @throws SQLException
     */
    public static String getUserPassword(String email) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet results;

        connection = getConnection(true);
        String query = "SELECT Password FROM users WHERE Username='" + email + "'";
        statement = connection.prepareStatement(query);
        results = statement.executeQuery();
        if(results.next()) {
            return results.getString("Password");
        }
        return null;
    }

    /**
     * Temporary method.
     * @param email User's registered email address
     * @return The First and Last name for the user registered with the given email address
     * @throws SQLException
     */
    public static String getUsersName(String email) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet results;

        connection = getConnection(true);
        String query = "SELECT * FROM users WHERE Username='" + email + "'";
        statement = connection.prepareStatement(query);
        results = statement.executeQuery();
        if(results.next()) {
            return results.getString("FirstName") + " " + results.getString("LastName");
        }
        return null;
    }
}

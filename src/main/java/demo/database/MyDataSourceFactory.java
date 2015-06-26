package demo.database;

import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
/**
 * Created by DanielY on 6/24/2015.
 */
public class MyDataSourceFactory {

    static private String databaseURL = "jdbc:mysql://localhost:3306/";
    static private String dbname = "demo";
    static private String username = "root";
    static private String password = "12345678";
    static private MysqlConnectionPoolDataSource mysqlDS = new MysqlConnectionPoolDataSource();

    static {
        mysqlDS.setURL(databaseURL + dbname + "?max-connections=4"); //Set max # Connections to 4, suppose to be most efficient?
        mysqlDS.setUser(username);
        mysqlDS.setPassword(password);
    }

    public static DataSource getMySQLDataSource() {
        return mysqlDS;
    }
}
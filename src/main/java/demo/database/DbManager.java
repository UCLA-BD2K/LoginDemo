package demo.database;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * Created by DanielY on 6/24/2015.
 */
public class DbManager {

    static private Connection ReadOnlyConnection;
    static private Connection WritableConnection;

    public static Connection getConnection(boolean readOnly) throws SQLException {
        DataSource ds = getDataSource();
        if(readOnly) {
                ReadOnlyConnection = ds.getConnection();
                ReadOnlyConnection.setReadOnly(true);
            return ReadOnlyConnection;
        }
        else {
                WritableConnection = ds.getConnection();
                WritableConnection.setReadOnly(false);
            return WritableConnection;
        }
    }

    public static DataSource getDataSource() {
        return MyDataSourceFactory.getMySQLDataSource();
    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

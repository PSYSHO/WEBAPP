package app.connection;

import java.sql.SQLException;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;

public class ConnectionPool {
    private ConnectionPool(){}
    private static BasicDataSource ods;
    static {
        ods = new BasicDataSource();
        ods.setUrl("jdbc:mysql://localhost:3306/mysql");
        ods.setUsername("root");
        ods.setPassword("pass");
        // ods.setConnectionCacheName(CACHE_NAME);
        Properties cacheProps = new Properties();
        cacheProps.setProperty("MinLimit", "0");
        cacheProps.setProperty("MaxLimit", "4");
        cacheProps.setProperty("InitialLimit", "1");
        cacheProps.setProperty("ConnectionWaitTimeout", "5");
        cacheProps.setProperty("ValidateConnection", "true");
        ods.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }
    public static java.sql.Connection getConnection() throws SQLException {
        return ods.getConnection();
    }
}

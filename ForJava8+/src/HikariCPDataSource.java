import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class HikariCPDataSource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    
    static {
    	config.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
    	config.setJdbcUrl("jdbc:jtds:sqlserver://127.0.0.1:1433/AdventureWorks2019;appName=HikariTest");
        config.setUsername("dbuser");
        config.setPassword("dbpassword");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        
        config.setMaximumPoolSize(50);  //最大連接數，預設10
        config.setMinimumIdle(15); //最小連接數，預設值和maximumPoolSize設定一樣
        config.setMaxLifetime(20); //maxLifetime設定，預設30分鐘
        config.setIdleTimeout(10);  //連線閒置設定，預設10分鐘
        
        config.setConnectionTestQuery("SELECT 1");
        ds = new HikariDataSource(config);
    }
    
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
     
    
    private HikariCPDataSource(){}
    
}

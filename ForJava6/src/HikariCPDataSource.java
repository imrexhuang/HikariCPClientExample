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
        config.setAutoCommit(false);//不自動Commit
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setMaximumPoolSize(50);  //最大連接數，預設10
        config.setMinimumIdle(15); //最小連接數，預設值和maximumPoolSize設定一樣
        config.setIdleTimeout(10);  //連線閒置設定，預設10分鐘
        config.setMaxLifetime(20); //maxLifetime設定，預設30分鐘
        //https://github.com/brettwooldridge/HikariCP#configuration-knobs-baby
        //If your driver supports JDBC4 we strongly recommend not setting this property.
        config.setConnectionTestQuery("SELECT 1");
        ds = new HikariDataSource(config);
    }
    
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();     
    }
	
    public static void closeConnection() throws SQLException {
        ds.close();
		// shutdown(): Deprecated. This method has been deprecated, please use close() instead
		// https://www.javadoc.io/doc/com.zaxxer/HikariCP/2.6.3/com/zaxxer/hikari/HikariDataSource.html#shutdown--
		// 新版已經移除shutdown() https://www.javadoc.io/doc/com.zaxxer/HikariCP/3.4.5/com/zaxxer/hikari/HikariDataSource.html#close--
    }    
    
    private HikariCPDataSource(){}
}

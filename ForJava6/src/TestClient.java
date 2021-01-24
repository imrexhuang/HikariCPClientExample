import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestClient {

	public static void main(String[] args) {
		ResultSet resultSet = null;
		Connection con = null;
		Statement statement = null;
		
        try{
        	con = HikariCPDataSource.getConnection(); 
            statement = con.createStatement();
        	
        	String selectSql = "SELECT TOP 10 * from [Person].[Person]";
        	
        	resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " "+resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(5));
            }
        
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	
            try{
                if(statement!=null)
                	statement.close();
             }catch(SQLException se2){
             }
            
             try{
                if(con!=null)
                	con.close();
             }catch(SQLException se){
                se.printStackTrace();
             }       
             
        }		

	}

}

package ConnectionFactory;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ConnectionFactory {
	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.DriverJDBCVersion";
	private static final String URL = "jdbc:sqlserver://puc-minas-tis2.database.windows.net:1433;database=db_tis2;user=lacarte@puc-minas-tis2;password=Pucminas2019;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

	
	
	public static Connection getConnection() {

			try {
				Class.forName(DRIVER);
				
				return DriverManager.getConnection(URL);
				
			} catch (ClassNotFoundException | SQLException e) {
				
				throw new RuntimeException("Erro na conexão ", e);
		}
	}
	
	public static void closeConnection(Connection con) {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	public static void closeConnection(Connection con, PreparedStatement stmt) {
		
		closeConnection(con);
		
		try {
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
			
			closeConnection(con, stmt);
			
			try {
				if(rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
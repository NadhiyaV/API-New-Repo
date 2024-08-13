package api.genericUtility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

/**
 * @author Nadhiya
 */
public class DataBaseUtility {
	static Connection conn=null;
	static ResultSet rs= null;
	static FileUtility fLib= new FileUtility();
	/**
	 * Connection toDB
	 * @throws IOException 
	 */
	public static void getConnectiontoDB() throws IOException {
		Driver driverRef;
		try {
			driverRef= new Driver();
			DriverManager.registerDriver(driverRef);
			DriverManager.getConnection(fLib.getDataFromPropertiesFile("DBUrl"),
					fLib.getDataFromPropertiesFile("DB_Username"),
					fLib.getDataFromPropertiesFile("DB_Password"));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void closeDB() throws SQLException {
		conn.close();
	}
	public static ResultSet executeQuery(String query) {
		try {
			rs=conn.createStatement().executeQuery(query);
			return rs;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	public boolean executeQueryVerifyAndGetData(String query,int columnIndex,String expectedData) throws SQLException {
		boolean flag=false;
		rs=conn.createStatement().executeQuery(query);
		while(rs.next()) {
			if(rs.getString(columnIndex).equals(expectedData)) {
				flag=true;
				break;
			}
		}
		if(flag==true) {
			System.out.println(expectedData+"=======data is verified in the DB");
			return true;
		}
		else {
			System.out.println(expectedData+"===========data is not verified with DB");
			return false;
		}
	}

}

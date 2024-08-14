package api.genericUtility;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;


/**
 * @author Nadhiya
 */
public class DataBaseUtility {
	static Connection conn;
	static ResultSet rs= null;
	static FileUtility fLib= new FileUtility();
	/**
	 * Connection toDB
	 * @throws IOException 
	 */
	public void getConnectiontoDB() throws IOException {
		
		try {
			 Driver driverRef= new Driver();
			DriverManager.registerDriver(driverRef);
			conn=DriverManager.getConnection(fLib.getDataFromPropertiesFile("DBUrl"),
					fLib.getDataFromPropertiesFile("DB_Username"),
					fLib.getDataFromPropertiesFile("DB_Password"));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	public void closeDB() throws SQLException {
		try {
		conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param query
	 * @return
	 */
	public  ResultSet executeQuery(String query) {
		try {
			rs=conn.createStatement().executeQuery(query);
			return rs;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	/**
	 * 
	 * @param query
	 * @param columnIndex
	 * @param expectedData
	 * @return
	 * @throws SQLException
	 */
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

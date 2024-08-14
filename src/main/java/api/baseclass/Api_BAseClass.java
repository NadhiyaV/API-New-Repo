package api.baseclass;
/**@author Nadhiya
 * 
 */

import java.sql.SQLException;


import org.apache.http.entity.ContentType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import api.genericUtility.*;
import com.ninza.hrm.api.genericUtility.JavaUtility;

import api.genericUtility.DataBaseUtility;
import io.restassured.builder.RequestSpecBuilder;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Api_BAseClass {
	
public JavaUtility jLib= new JavaUtility();
public FileUtility fLib= new FileUtility();
public DataBaseUtility dbLib= new DataBaseUtility();
public static RequestSpecification reqspeciobj;
public static ResponseSpecification resspeciobj;
@BeforeSuite
public void createConnectionWithDB() throws Throwable {
	dbLib.getConnectiontoDB();
	System.out.println("=============================Connection toDB=================");
	RequestSpecBuilder reqspec= new RequestSpecBuilder()
		.setBaseUri(fLib.getDataFromPropertiesFile("BASEUri"))
		.setContentType(io.restassured.http.ContentType.JSON);
	//.addHeader("param1", "value1");
	reqspeciobj=reqspec.build();
ResponseSpecBuilder respspecb=new ResponseSpecBuilder();
	respspecb.expectContentType(io.restassured.http.ContentType.JSON);
resspeciobj=	respspecb.build();
}
@AfterSuite
public void configAs() throws SQLException {
dbLib.closeDB();
System.out.println("=======================Close the Connection===================");
}
}

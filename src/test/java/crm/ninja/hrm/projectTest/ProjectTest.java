package crm.ninja.hrm.projectTest;

import static io.restassured.RestAssured.*;

import java.sql.SQLException;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import api.baseclass.Api_BAseClass;
import io.restassured.response.Response;
import IEndPoint.iendPoint;

import pojoUtility.Project;

public class ProjectTest extends Api_BAseClass{
	String projectName;
	Project pobj;
	
	@Test
	public void addSingleProjectwithcreated() throws SQLException {
		String expectedData="Successfully Added";
		projectName="RRR_Info_tech"+jLib.getRandomNumber();
		pobj= new Project("Nadhiya",projectName,"Created",5);
		Response resp= given()
		.spec(reqspeciobj)
		.body(pobj)
		
	.when()
		.post(iendPoint.AddProject);
	resp.then()
		.assertThat().statusCode(201)
		.assertThat().time(Matchers.lessThan(7000L))
		.log().all()
		.spec(resspeciobj);
	long restime=resp.time();
	resp.then().assertThat().time(Matchers.lessThan(7000l));
	String actmsg=resp.jsonPath().get("msg");
	Assert.assertEquals(expectedData,actmsg,"Matched");
	boolean flag=dbLib.executeQueryVerifyAndGetData("select * from project", 4, projectName);
	Assert.assertTrue(flag, "Project is not validated with DB");
	
		}
}

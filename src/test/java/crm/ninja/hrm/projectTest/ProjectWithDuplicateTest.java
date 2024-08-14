package crm.ninja.hrm.projectTest;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import IEndPoint.iendPoint;
import api.baseclass.Api_BAseClass;
import io.restassured.response.Response;
import pojoUtility.Project;

public class ProjectWithDuplicateTest extends Api_BAseClass{
@Test
public void projectwithDuplicatetest() {
	String projectName="EndicusInfoTech";
	Project pobj;
	String expectedmsg ="Successfully Added";
	String query="select * from project";
	pobj=new Project("Navya",projectName,"OnGoing",0);
	Response resp =given()
			.spec(reqspeciobj)
			.body(pobj)
			.when()
				.post(iendPoint.AddProject);
			resp.then()
				.assertThat().statusCode(201)
				//.time(Matchers.lessThanOrEqualTo(300l))
				.log().all();
	
}
}

package crm.ninja.hrm.projectTest;

import static io.restassured.RestAssured.given;

import java.sql.SQLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.genericUtility.*;
import api.baseclass.*;
import IEndPoint.iendPoint;
import io.restassured.response.Response;
import pojoUtility.Project;

public class ProjectWithCompletedStatusTest extends Api_BAseClass{
	String projectName;
	Project pobj;
@Test
public void projectwithcompletedstatustest() throws SQLException {
	String expectedmsg ="Successfully Added";
	String query="select * from project";
	projectName="Nad_TY_"+jLib.getRandomNumber();
		pobj=new Project("Navya",projectName,"Completed",0);
	Response resp =given()
			.spec(reqspeciobj)
			.body(pobj)
			.when()
				.post(iendPoint.AddProject);
			resp.then()
				.assertThat().statusCode(201)
				//.time(Matchers.lessThanOrEqualTo(300l))
				.log().all();
			String actmsg=resp.jsonPath().get("msg");
			Assert.assertEquals(expectedmsg,actmsg,"Matched");
			String PROJECTNAME=resp.jsonPath().get("projectName");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://49.249.28.218:8091");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//input[@class='form-control' and @placeholder='Search by Project Id']")).sendKeys(PROJECTNAME,Keys.ENTER);
		String projectname=driver.findElement(By.xpath("//tbody//td[text()='"+PROJECTNAME+"']")).getText();
		Assert.assertEquals(PROJECTNAME, projectname,"verified with GUI");
		//validate with DB
		boolean flag= dbLib.executeQueryVerifyAndGetData(query, 4, projectName);
		Assert.assertTrue(flag,"db is not validated");
}
}

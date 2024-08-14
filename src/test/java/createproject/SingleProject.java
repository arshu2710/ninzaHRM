package createproject;


import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.genericutility.DataBaseUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoclass.Utility.ProjectPojo;

import static io.restassured.RestAssured.given;

import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

public class SingleProject {
	@Test
 public static void addSingleProjectWithCreated() {
		/*
		 * JSONObject js= new JSONObject(); js.put("createdBy", "Ashu1"); js.put(
		 * "projectName", "tkp11"); js.put("status", "Created"); js.put("teamSize",
		 * "0");
		 */
 Random random = new Random();
	int ranNum =random.nextInt(5000);
	ProjectPojo pObj = new ProjectPojo("tkp_"+ranNum,"Created","Ashu",0);

	Response resp=given()
	.contentType(ContentType.JSON)
	.body(pObj)
	.when()
	.post("http://49.249.28.218:8091/addProject");
	resp.then().assertThat().statusCode(201);
	resp.then().log().all();
	resp.then().assertThat().body("msg", Matchers.equalTo("Successfully Added"));
	String projname=resp.jsonPath().get("projectName");
	System.out.println(projname);
	String projid=resp.jsonPath().get("projectId");
	System.out.println(projid);
	String status=resp.jsonPath().get("status");
	System.out.println(status);
	resp.then().assertThat().body("projectName", Matchers.equalTo(projname));
	WebDriver driver =new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.get("http://49.249.28.218:8091");
	driver.findElement(By.id("username")).sendKeys("rmgyantra");
	driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
	driver.findElement(By.xpath("//button[@class=\"btn btn-primary btn-lg btn-block\"]")).click();
	driver.findElement(By.xpath("//a[text()='Projects']")).click();
	driver.findElement(By.xpath("//input[@placeholder='Search by Project Id']")).sendKeys(projid);
	String text=driver.findElement(By.xpath("//td[text()='"+projid+"']/parent::tr[@class=\"tr\"]/descendant::td[.='"+status+"']")).getText();
	Assert.assertEquals(text,status);
}
	@Test
	public static void addProjectWitONGoingStatus() throws SQLException {
		 Random random = new Random();
			int ranNum =random.nextInt(5000);
			ProjectPojo pObj = new ProjectPojo("tkp_"+ranNum,"OnGoing","Ashwini",0);

			Response resp=given()
			.contentType(ContentType.JSON)
			.body(pObj)
			.when()
			.post("http://49.249.28.218:8091/addProject");
			resp.then().assertThat().statusCode(201);
			resp.then().log().all();
			resp.then().assertThat().body("msg", Matchers.equalTo("Successfully Added"));
			
			String projname=resp.jsonPath().get("projectName");
			System.out.println(projname);
			String projid=resp.jsonPath().get("projectId");
			System.out.println(projid);
			String status=resp.jsonPath().get("status");
			System.out.println(status);
			
			resp.then().assertThat().body("projectName", Matchers.equalTo(projname));	
			DataBaseUtility dlib=new DataBaseUtility();
			boolean flag=dlib.executeQueryVerifyAndGetData("select * from project", 4, projname);
			Assert.assertTrue(flag,"project in db is not verified");
			WebDriver driver =new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get("http://49.249.28.218:8091");
			driver.findElement(By.id("username")).sendKeys("rmgyantra");
			driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
			driver.findElement(By.xpath("//button[@class=\"btn btn-primary btn-lg btn-block\"]")).click();
			driver.findElement(By.xpath("//a[text()='Projects']")).click();
			driver.findElement(By.xpath("//input[@placeholder='Search by Project Id']")).sendKeys(projid);
			String text=driver.findElement(By.xpath("//td[text()='"+projid+"']/parent::tr[@class=\"tr\"]/descendant::td[.='"+status+"']")).getText();
			Assert.assertEquals(text,status);
}
	@Test
	public static void addProjectWithCompletedStats() throws SQLException {
		 Random random = new Random();
			int ranNum =random.nextInt(5000);
			ProjectPojo pObj = new ProjectPojo("tkp_"+ranNum,"Completed","Ashwini",0);

			Response resp=given()
			.contentType(ContentType.JSON)
			.body(pObj)
			.when()
			.post("http://49.249.28.218:8091/addProject");
			resp.then().assertThat().statusCode(201);
			resp.then().log().all();
			resp.then().assertThat().body("msg", Matchers.equalTo("Successfully Added"));
			
			String projname=resp.jsonPath().get("projectName");
			System.out.println(projname);
			String projid=resp.jsonPath().get("projectId");
			System.out.println(projid);
			String status=resp.jsonPath().get("status");
			System.out.println(status);
			resp.then().assertThat().body("projectName", Matchers.equalTo(projname));
			
			DataBaseUtility dlib=new DataBaseUtility();
			boolean flag=dlib.executeQueryVerifyAndGetData("select * from project", 4, projname);
			Assert.assertTrue(flag,"project in db is not verified");
			
			WebDriver driver =new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get("http://49.249.28.218:8091");
			driver.findElement(By.id("username")).sendKeys("rmgyantra");
			driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
			driver.findElement(By.xpath("//button[@class=\"btn btn-primary btn-lg btn-block\"]")).click();
			driver.findElement(By.xpath("//a[text()='Projects']")).click();
			driver.findElement(By.xpath("//input[@placeholder='Search by Project Id']")).sendKeys(projid);
			String text=driver.findElement(By.xpath("//td[text()='"+projid+"']/parent::tr[@class=\"tr\"]/descendant::td[.='"+status+"']")).getText();
			Assert.assertEquals(text,status);
}
	@Test
	public static void addDuplicateProject() {
	ProjectPojo pObj = new ProjectPojo("tkp","Created","Ashwini",0);
	Response resp=given()
	.contentType(ContentType.JSON)
	.body(pObj)
	.when()
	.post("http://49.249.28.218:8091/addProject");
	resp.then().assertThat().statusCode(201);
	resp.then().log().all();
	String projname=resp.jsonPath().get("projectName");
	ProjectPojo pOb = new ProjectPojo("tkp","Created","Ashwini",0);
	resp=given()
			.contentType(ContentType.JSON)
			.body(pOb)
			.when()
			.post("http://49.249.28.218:8091/addProject");
			resp.then().assertThat().statusCode(409);
			resp.then().log().all();
}
	
	@Test
	public static void addProjectautomate() {
		WebDriver driver =new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://49.249.28.218:8091");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[@class=\"btn btn-primary btn-lg btn-block\"]")).click();
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		driver.findElement(By.name("projectName")).sendKeys("pr");
		driver.findElement(By.name("createdBy")).sendKeys("ash");
		WebElement dropdown =driver.findElement(By.xpath("//label[@class='col-form-label']/following-sibling::select[@name=\"status\"]"));
		Select s=new Select(dropdown);
		s.selectByVisibleText("Created");
		driver.findElement(By.xpath("//input[@class=\"btn btn-success\"]")).click();
	
	}
	
}
package apiAutomation;
import files.ReusableMethods;
import files.payLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class basic6Jira1 {

	
	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException{
		FileInputStream fis = new FileInputStream("C:\\Users\\omelik079\\workspace\\APIproject\\src\\files\\env.properties");
		prop.load(fis);
		
	}
	
	@Test
	public static void jiraAPI(){

		RestAssured.baseURI = "http://localhost:8088";
		Response res = 
				given().header("Content-Type", "application/json").
				header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionKey()).
				body(payLoad.postJiraBug("First Summary", "Here my big description")).log().all().
				when().post("/rest/api/2/issue/").then().assertThat().statusCode(201).extract().response();
		
		JsonPath js = ReusableMethods.rawToJson(res);
		String id = js.get("id");
		System.out.println(id);
	}
	
	@Test
	public static void jiraAPI1(){

		RestAssured.baseURI = "http://localhost:8088";
		Response res = 
				given().header("Content-Type", "application/json").
				header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionKey()).
				body(payLoad.postJiraBug("First Summary", "Here my big description")).log().all().
				when().post("/rest/api/2/issue/").then().assertThat().statusCode(201).extract().response();
		
		JsonPath js = ReusableMethods.rawToJson(res);
		String id = js.get("id");
		System.out.println(id);
	}
	
}

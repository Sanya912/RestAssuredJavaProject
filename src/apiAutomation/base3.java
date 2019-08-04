package apiAutomation;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import files.payLoad;


public class base3 {


	
	@Test
	public void AddAndDelete(){
		//Task 1 - Grab the response
		RestAssured.basePath = "http://216.10.245.166";
		Response res=given().
				
		queryParam("key", "qaclick123").
		body(payLoad.getPostData()).
	when().post("/maps/api/place/add/json").
	then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	body("status", equalTo("OK")).
	extract().response();
		
	//Task 2 - Grab the Place ID from response	
	String responseString=res.asString();
	System.out.println(responseString);

				//String to JSON format
	JsonPath js = new JsonPath(responseString);
	String placeId = js.get("place_id");
	System.out.println(placeId);
	
	//Task 3 place this id in the Delete request
	given().
	queryParam("key", "qaclick123").
	body("{"+
    "\"place_id\":\""+placeId +"\"" +
"}").
	when().post("/maps/api/place/add/json").then().
	assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	body("status", equalTo("OK"));
	
	
	
	}
}

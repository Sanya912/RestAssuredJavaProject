import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import files.resources;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;



public class Main {

	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException{
		
		FileInputStream fis = new FileInputStream("C:\\Users\\omelik079\\workspace\\APIproject\\src\\files\\env.properties");
		prop.load(fis);
		
	}

	@Test
	public void test(){
		
		RestAssured.baseURI = prop.getProperty("HOST");
		
		given().
			param("location", "-33.8670522,151.1957362").
			param("radius", "500").
			param("key", prop.getProperty("KEY")).log().all().
			when().get(resources.placePostData()).
			then().assertThat().statusCode(200).and().contentType(ContentType.JSON).log().body()/*.and().
			body("results[0].name", equalTo("Sydney")).and().
			body("results[0].place_id", equalTo("dsdssdd")).and().
			header("Server", "responseTest")*/;
		
		//error in Postman maps
		
		/*header("dft", "fefef"),
		cookie("sdsa", "dsa").*/
	}
	
}

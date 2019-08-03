import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import files.ReusableMethods;
import files.payLoad;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class staticJson {
	
	//method reading xml 
	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	@Test(priority = 1) // added dataProvider and parameters
	public void addBook() throws IOException{
		
		RestAssured.baseURI = "http://216.10.245.166";  
		Response resp = given().header("Content-Type", "application/json").
				//need to change pages or name in the file. Numbers should be unique
		body(GenerateStringFromResource("C:\\Users\\omelik079\\Desktop\\Automation\\APIAutomation\\testAPIfiles\\jsonTest.json")).log().all().
	when().post("/Library/Addbook.php").
	then().assertThat().statusCode(200).
	extract().response();

		
		JsonPath js = ReusableMethods.rawToJson(resp);
		String id = js.get("ID");
		System.out.println(id);
	}

}

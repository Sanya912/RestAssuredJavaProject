import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class basic4 extends Main{
	
	//method reading xml 
	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	@Test
	public void postData() throws IOException{
		String postdata = GenerateStringFromResource("C:\\Users\\omelik079\\Desktop\\Automation\\APIAutomation\\testAPIfiles\\postdata.xml");
		//RestAssured.basePath = "http://216.10.245.166";
		RestAssured.basePath = prop.getProperty("HOST");  
		Response resp = given().
		queryParam("key", prop.getProperty("KEY")).
		//body(postdata).
	when().post("/maps/api/place/add/xml").
	then().assertThat().statusCode(200).and().contentType(ContentType.XML).and().
	extract().response();
		
		XmlPath x =ReusableMethods.rawToXML(resp);
		System.out.println(x.get("PlaceAddResponse.place_id"));
	}

}

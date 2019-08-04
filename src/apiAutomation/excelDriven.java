package apiAutomation;
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

public class excelDriven {
	
	//method reading xml 
	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	public static int numbs(){
		return (int)(Math.random()*99999);
	}
	
	@Test(priority = 2,dataProvider="BooksData") // added dataProvider and parameters
	public void addBook(String name, Integer num) throws IOException{
		
		RestAssured.baseURI = "http://216.10.245.166";  
		Response resp = given().header("Content-Type", "application/json").
		body(payLoad.Addbook(name, num)).log().all().
	when().post("/Library/Addbook.php").
	then().assertThat().statusCode(200).
	extract().response();

		
		JsonPath js = ReusableMethods.rawToJson(resp);
		String id = js.get("ID");
		System.out.println(id);
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getDataExcel(){
		//multidenmentionArray = collection of arrays
		return new Object[][]{ {"Leo", numbs()}, {"Aleks5", numbs()}, {"Alik", numbs()} };
		 
	}
	
	@Test(priority = 1) // added dataProvider and parameters
	public void addBook() throws IOException{
		
		RestAssured.baseURI = "http://216.10.245.166";  
		Response resp = given().header("Content-Type", "application/json").
		body(payLoad.Addbook("Alek", (int)(Math.random()*99999))).log().all().
	when().post("/Library/Addbook.php").
	then().assertThat().statusCode(200).
	extract().response();

		
		JsonPath js = ReusableMethods.rawToJson(resp);
		String id = js.get("ID");
		System.out.println(id);
	}

}

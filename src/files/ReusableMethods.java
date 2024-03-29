package files;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {

	
	
	public static XmlPath rawToXML(Response r){
		String respond = r.asString();
		XmlPath x= new XmlPath(respond);
		return x;
	}
	
	public static JsonPath rawToJson(Response r){
		String respond = r.asString();
		JsonPath x= new JsonPath(respond);
		return x;
	}
	
	public static String getSessionKey(){
		RestAssured.baseURI = "http://localhost:8088";
		Response res = given().header("Content-Type", "application/json").
		body("{ \"username\": \"melikhov911\", \"password\": \"Jordan23@\" }").when().post("/rest/auth/1/session").
		then().statusCode(200).extract().response();
		
		JsonPath js = ReusableMethods.rawToJson(res);
		String sesid = js.get("session.value");
		return sesid;
	}
	
}

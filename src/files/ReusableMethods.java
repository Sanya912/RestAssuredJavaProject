package files;

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
	
}

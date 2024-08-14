package com.ninza.hrm.api.genericutility;

	import java.io.IOException;
import java.util.List;

	import com.jayway.jsonpath.JsonPath;

	import static io.restassured.RestAssured.*;
	import io.restassured.response.Response;
	import net.bytebuddy.NamingStrategy.Suffixing.BaseNameResolver.ForGivenType;

	public class JsonUtility {
		FileUtility fLib =new FileUtility();

		//To get Jsondata based on json complex xpath
		public String getDataOnJsonPath(Response resp , String jsonXPath) {
			List<Object> list =JsonPath.read(resp.asString(), jsonXPath);
			return list.get(0).toString();
		}
		
		//To get the xmldata based on xml complex xpath
		public String getDataOnXpathPath(Response resp, String xmlXPath) {
			return resp.xmlPath().get(xmlXPath);
		}
		
		//To verify the data in jsonbody based on jsonpath
		public boolean VerifyDataOnJsonPath(Response resp, String jsonXpath,String expectedData) {
			List<String> list = JsonPath.read(resp.asString(), jsonXpath);
			boolean flag = false;
			for(String str :list) {
				if(str.equals(expectedData)) {
					System.out.println(expectedData+ "is available ==Pass");
					flag=true;
				}
			}
			if(flag ==false) {
				System.out.println(expectedData+ "is not available ==pass");
			}
			return flag;
		}
		
		public String getAccessToken() throws IOException  {
			 Response resp=given()
						.formParam("client_id", fLib.getDataFromPropertiesFile("ClientID"))
						.formParam("client_secret", fLib.getDataFromPropertiesFile("ClientSecret"))
						.formParam("grant_type", "client_credentials")
						.when()
						.post("http://49.249.28.218:8180/auth/realms/ninza/protocol/openid-connect/token");
						resp.then()
						.log().all();
						
						//capture data from the response 
						String token = resp.jsonPath().get("access_token");
						return token;
			
		}
		  
	}



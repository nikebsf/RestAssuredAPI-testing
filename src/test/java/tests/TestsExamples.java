package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

//import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestsExamples {
	
	
	
	@Test
	public void test1() {
//		we no longe required ressassured.get because we imported static * 
//		Response res = RestAssured.get("https://reqres.in/api/users?page=2");
		Response res = get("https://reqres.in/api/users?page=2");
		
		System.out.println("Status Code: "+res.getStatusCode()+"\n"+"Time: "+res.getTime());
		
		System.out.println(res.getBody().asString());
		System.out.println(res.getStatusLine());
		System.out.println(res.getHeader("content-type"));

		int StatusCode = res.getStatusCode();
		
		Assert.assertEquals(StatusCode, 201);
		
	}
	
	@Test
	public void test2() {
		
		baseURI = "https://reqres.in/api";
		
		given().
			get("/users?page=2").
		then().
			statusCode(200).body("data[1].id", equalTo(8));
	}

}

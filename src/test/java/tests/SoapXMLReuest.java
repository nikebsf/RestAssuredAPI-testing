package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class SoapXMLReuest {
	@Test
	public void validateSoapXML() throws IOException {
		File file = new File("./SoapRequests/add.xml");
		
		if(file.exists())
			System.out.println("  >> File exists >>");
		else
			System.out.println("  << file does not exists");
		
		FileInputStream fis = new FileInputStream(file);
		
		String requestBody = IOUtils.toString(fis, "UTF-8");
		baseURI = "https://ecs.syr.edu";
		
		given().
			contentType("text/xml").
			accept(ContentType.XML).
			body(requestBody).
		when().
			post("/faculty/fawcett/Handouts/cse775/code/calcWebService/Calc.asmx").
		then().
			statusCode(200).log().all().
		and().
			body("//*:AddResult/text()", equalTo("5"));
	}

}

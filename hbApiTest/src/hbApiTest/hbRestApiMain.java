package hbApiTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class hbRestApiMain {

	public static void main(String[] args) {
		
		http://burak.free.beeceptor.com/allGrocery
		RestAssured.baseURI = "http://burak.free.beeceptor.com/allGrocery";
		//
		//Response resp=RestAssured.get(baseURL);
		
		//int code=resp.getStatusCode();
		
		//System.out.println("Status code is" + code);

/*
		   get("/events?id=390").then().statusCode(200).assertThat()
		   .body("data.leagueId", equalTo(35)); 
	*/	
		

	}

}

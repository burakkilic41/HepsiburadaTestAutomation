package hbAPI.hbAPI;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class AppTest 
{
	
	public String baseURL="http://kilicburak.free.beeceptor.com/"; //Mock service base url
	public String getEndPoint="allGrocery/";
	public String name1="apple";
	public String postEndPoint="add";
	
    @Test
    /*
     * For  an endpoint  which  returns stock price information service;
     * id, name, price and stock are checked,
     * The response code is checked,
     * The response time is checked and logged.
     */
    public void getallGrocery()
    {
        given()
        	.get(baseURL +"allGrocery")
        .then()
        	.statusCode(200)
        	.body("data.id[0]", equalTo(1))
        	.body("data.name", hasItems("apple","grapes"))
        	.body("data.name[0]", equalTo("apple"))
        	.body("data.price[0]", equalTo(3))
        	.body("data.stock[0]", equalTo(100))
        	.body("data.id[1]", equalTo(2))
        	.body("data.name[1]", equalTo("grapes"))
        	.body("data.price[1]", equalTo(5))
        	.body("data.stock[1]", equalTo(50))
        	.and().time(lessThan(3000L))
        	.log().all();
        
    }
    
    @Test
    /*
     * For an endpoint which returns by given name;
     * id, name, price and stock are checked,
     * The response code is checked,
     * The response time is checked and logged.
     */
    public void getallGroceryName()
    {
        given()	
        	.get(baseURL + getEndPoint + name1)
        .then()
        	.statusCode(200)
        	.body("data.id[0]", equalTo(1))
        	.body("data.name[0]", equalTo(name1))
        	.body("data.price[0]", equalTo(3))
        	.body("data.stock[0]", equalTo(100))
        	.and().time(lessThan(3000L))
        	.log().all();

    }
    
    @Test
    /*
     * For an endpoint which returns by add new products;
     * id, name, price and stock are checked,
     * The response code is checked,
     * The response time is checked and logged.
     */
    public void postAdd()
    {
    	given()
    		.post(baseURL + postEndPoint)
        .then()
    		.statusCode(200)
    		.body("id", equalTo(4))
    		.body("name", equalTo("string"))
    		.body("price", is(12.3f))
    		.body("stock", equalTo(3))
    		.and().time(lessThan(3000L))
    		.log().all();
    }

    
    
}

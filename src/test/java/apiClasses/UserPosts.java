package apiClasses;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class UserPosts {
private Properties prop;
private String baseURI;
private String endPoint;
private Response response;
private int statusCode;
public void setUserPostEndPoint(String resource){
    prop=new Properties();
    try {
        prop.load(UserPosts.class.getClassLoader().getResourceAsStream("config.properties"));
        baseURI=prop.getProperty("baseURI");
        System.out.println("Base URI:"+baseURI);
        endPoint=baseURI+"/"+resource;
        System.out.println("User Post end point: "+endPoint);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
public void hitEndpointWithGetMethod(){
    response= given().log().all().get(endPoint);
}
public void verifyResponseStatusCode(int expectedStatusCode){
    statusCode=response.getStatusCode();
    System.out.println("Response status code:"+statusCode);
    Assert.assertTrue(statusCode==expectedStatusCode);
    //Assert.assertEquals(statusCode,expectedStatusCode);
    System.out.println("Expected status code is matching with actual status code");
}
public void verifyResponseFormat(){
    response.then().assertThat().contentType(ContentType.JSON);
    System.out.println("API response is in JSON format");
    System.out.println("API response in String is :"+response.asString());
}
}

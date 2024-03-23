package apiClasses;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import pojoClasses.UserPostsPojo;

import java.io.IOException;
import java.util.HashMap;
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
    System.out.println("============Response status code:"+statusCode);
    Assert.assertTrue(statusCode==expectedStatusCode);
    //Assert.assertEquals(statusCode,expectedStatusCode);
    System.out.println("=====================Expected status code is matching with actual status code");
}
public void verifyResponseFormat(){
    response.then().assertThat().contentType(ContentType.JSON);
    System.out.println("===================API response is in JSON format");
    System.out.println("==================API response in String is :"+response.asString());
}
public void hitEndPointWithHTTPPostMethod(String id, String title,String author){
    HashMap<String,String>postRequestBody= new HashMap<String,String>();
    postRequestBody.put("id",id);
    postRequestBody.put("title",title);
    postRequestBody.put("author",author);
    /*UserPostsPojo postRequestBody= new UserPostsPojo();
    postRequestBody.setId(id);
    postRequestBody.setTitle(title);
    postRequestBody.setAuthor(author);*/
    response=given().log().all().when().contentType(ContentType.JSON).body(postRequestBody).post(endPoint);
    System.out.println("=================User hit user post end point with post http method");
}
public void verifyPostObjectCreation(String id,String title, String author){
    JsonPath jsonPath= new JsonPath(response.asString());
    Assert.assertEquals("post id is not found",id,jsonPath.get("id"));
    Assert.assertEquals("title  is not found",title,jsonPath.get("title"));
    Assert.assertEquals("author  is not found",author,jsonPath.get("author"));
//    Assert.assertTrue("post id is not found",response.getBody().asString().contains(id));
//    Assert.assertTrue("title is not found",response.getBody().asString().contains(title));
//    Assert.assertTrue("author is not found",response.getBody().asString().contains(author));
    System.out.println("================Post object is created and expected values are matching with API response.....");

}
}

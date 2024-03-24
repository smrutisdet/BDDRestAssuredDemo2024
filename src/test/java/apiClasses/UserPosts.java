package apiClasses;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import pojoClasses.UserPostsPojo;
import utilities.BaseSteps;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import static io.restassured.RestAssured.given;
public class UserPosts {
    private Properties prop;
    private String endPoint;
    private Response response;
    private int statusCode;
    private BaseSteps baseSteps;
    private String UpdateByIdUsingPutEndPoint;
    private String UpdateByIdUsingPatchEndPoint;
    private String deleteByIdUsingDeleteMethodEndPoint;

    public void setUserPostEndPoint(String resource) {
        baseSteps = new BaseSteps();
        endPoint = baseSteps.getBaseURI() + "/" + resource;
        System.out.println("========================User Post end point: " + endPoint);
    }

    public void hitEndpointWithGetMethod() {
        response = given().log().all().get(endPoint);
    }

    public void verifyResponseStatusCode(int expectedStatusCode) {
        statusCode = response.getStatusCode();
        System.out.println("============Response status code:" + statusCode);
        Assert.assertTrue(statusCode == expectedStatusCode);
        //Assert.assertEquals(statusCode,expectedStatusCode);
        System.out.println("=====================Expected status code is matching with actual status code");
    }

    public void verifyResponseFormat() {
        response.then().assertThat().contentType(ContentType.JSON);
        System.out.println("===================API response is in JSON format");
        System.out.println("==================API response in String is :" + response.asString());
    }

    public void hitEndPointWithHTTPPostMethod(String id, String title, String author) {
        HashMap<String, String> postRequestBody = new HashMap<String, String>();
        postRequestBody.put("id", id);
        postRequestBody.put("title", title);
        postRequestBody.put("author", author);
    /*UserPostsPojo postRequestBody= new UserPostsPojo();
    postRequestBody.setId(id);
    postRequestBody.setTitle(title);
    postRequestBody.setAuthor(author);*/
        response = given().log().all().when().contentType(ContentType.JSON).body(postRequestBody).post(endPoint);
        System.out.println("=================User hit user post end point with post http method");
    }

    public void verifyPostObjectCreation(String id, String title, String author) {
        JsonPath jsonPath = new JsonPath(response.asString());
        Assert.assertEquals("post id is not found", id, jsonPath.get("id"));
        Assert.assertEquals("title  is not found", title, jsonPath.get("title"));
        Assert.assertEquals("author  is not found", author, jsonPath.get("author"));
//    Assert.assertTrue("post id is not found",response.getBody().asString().contains(id));
//    Assert.assertTrue("title is not found",response.getBody().asString().contains(title));
//    Assert.assertTrue("author is not found",response.getBody().asString().contains(author));
        System.out.println("================Post object is created and expected values are matching with API response.....");

    }

    public void hitEndPointWithPutMethod(String id, String title, String author) {
        UpdateByIdUsingPutEndPoint = endPoint + "/" + id;
        System.out.println("==========Put By id end point is:" + UpdateByIdUsingPutEndPoint);
        HashMap<String, String> putRequestBody = new HashMap<String, String>();
        putRequestBody.put("id", id);
        putRequestBody.put("title", title);
        putRequestBody.put("author", author);
        response = given().log().all().when().contentType(ContentType.JSON).body(putRequestBody).put(UpdateByIdUsingPutEndPoint);
        System.out.println("=============Hit the end point with put Method call");
    }

    public void verifyPutMethodUpdates(String id, String title, String author) {
        if (response.getStatusCode() != 404) {//object is present
            JsonPath jsonPath = new JsonPath(response.asString());
            Assert.assertEquals("put id is not found", id, jsonPath.get("id"));
            Assert.assertEquals("title  is not found", title, jsonPath.get("title"));
            Assert.assertEquals("author  is not found", author, jsonPath.get("author"));
            System.out.println("=======Put has updated the mentioned object");
        }
    }
    public void hitEndPointWithPatchMethod(String id, String author){
        HashMap<String, String> patchRequestBody = new HashMap<String, String>();
        patchRequestBody.put("author",author);
        UpdateByIdUsingPatchEndPoint=endPoint + "/" + id;
        response = given().log().all().when().contentType(ContentType.JSON).body(patchRequestBody).patch(UpdateByIdUsingPatchEndPoint);
        System.out.println("=============Hit the end point with patch Method call");

}
public void verifyPatchMethodUpdates(String id, String author){
        JsonPath jsonPath= new JsonPath(response.asString());
    Assert.assertEquals("patch id is not found", id, jsonPath.get("id"));
    Assert.assertEquals("author  is not found", author, jsonPath.get("author"));
    System.out.println("=======Patch has updated the mentioned object");
}
public void hitEndpointWithDeleteMethod(String id){
    deleteByIdUsingDeleteMethodEndPoint= endPoint + "/" + id;
    response = given().log().all().when().delete(deleteByIdUsingDeleteMethodEndPoint);
    System.out.println("==========response after delete call is:"+response.asString());
}
public void verifyObjectIsDeleted(String id){
    response = given().log().all().when().get(deleteByIdUsingDeleteMethodEndPoint);
    Assert.assertEquals("Object is not deleted",404,response.getStatusCode());
}
}

package stepDefinitions;

import apiClasses.UserPosts;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserPostsStepdefs {
    UserPosts uPosts=new UserPosts();
    @Given("user sets the api end point with {string}")
    public void userSetsTheApiEndPointWithResource(String resource) {
        uPosts.setUserPostEndPoint(resource);
    }

    @When("user hits the end point with get method")
    public void userHitsTheEndPointWithGetMethod() {
        uPosts.hitEndpointWithGetMethod();
    }

    @Then("api should return valid {string} in response")
    public void apiShouldReturnValidStatusCodeInResponse(String expectedStatusCode) {
        uPosts.verifyResponseStatusCode(Integer.parseInt(expectedStatusCode));
    }

    @And("Response should be in json format")
    public void responseShouldBeInJsonFormat() {
        uPosts.verifyResponseFormat();
    }

    @When("user hits the end point with post method by passing valid {string} and {string} and {string}")
    public void userHitsTheEndPointWithPostMethodByPassingValidIdAndTitleAndAuthor(String id, String title,String author) {
        uPosts.hitEndPointWithHTTPPostMethod(id,title,author);

    }

    @And("API should return created data in response body with {string} and {string} and {string}")
    public void apiShouldReturnCreatedDataInResponseBodyWithIdAndTitleAndAuthor(String id, String title,String author) {
    uPosts.verifyPostObjectCreation(id,title,author);
    }

    @When("user hits the end point with put method by passing valid {string} and {string} and {string}")
    public void userHitsTheEndPointWithPutMethodByPassingValidIdAndTitleAndAuthor(String id, String title,String author) {
    uPosts.hitEndPointWithPutMethod( id,  title, author);
    }

    @And("API should return updated data in response body with {string} and {string} and {string}")
    public void apiShouldReturnUpdatedDataInResponseBodyWithIdAndTitleAndAuthor(String id, String title,String author) {
        uPosts.verifyPutMethodUpdates(id,  title, author);
    }
}

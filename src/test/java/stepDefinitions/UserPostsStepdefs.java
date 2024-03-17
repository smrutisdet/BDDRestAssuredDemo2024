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
}

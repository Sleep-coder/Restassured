package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostsStepDefs {

    private Response response;

    @Given("I hit the url of posts API endpoint")
    public void i_hit_the_url_of_posts_API_endpoint() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }

    @When("I send a GET request to retrieve all posts")
    public void i_send_a_GET_request_to_retrieve_all_posts() {
        response = given().when().get("posts");
    }

    @Then("I receive a response with status code {int}")
    public void i_receive_a_response_with_status_code(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @Then("the response should contain a non-empty list of posts")
    public void the_response_should_contain_a_non_empty_list_of_posts() {
        JsonPath jsonPath = response.jsonPath();
        int postCount = jsonPath.getList("$").size();
        assert(postCount > 0);
    }

    @When("I send a POST request to create a new post")
    public void i_send_a_POST_request_to_create_a_new_post() {
        String requestBody = "{\n" +
                "    \"title\": \"New Post\",\n" +
                "    \"body\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit\",\n" +
                "    \"userId\": 1\n" +
                "}";
        response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("posts");
    }

    @Then("the response should contain the created post details")
    public void the_response_should_contain_the_created_post_details() {
        JsonPath jsonPath = response.jsonPath();
        assertEquals("New Post", jsonPath.getString("title"));
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit", jsonPath.getString("body"));
        assertEquals(1, jsonPath.getInt("userId"));
    }

    @When("I send a PUT request to update an existing post")
    public void i_send_a_PUT_request_to_update_an_existing_post() {
        String requestBody = "{\n" +
                "    \"title\": \"Updated Post\",\n" +
                "    \"body\": \"Updated body\",\n" +
                "    \"userId\": 1\n" +
                "}";
        response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put("posts/1"); // Assuming updating the first post
    }

    @When("I send a DELETE request to delete an existing post")
    public void i_send_a_DELETE_request_to_delete_an_existing_post() {
        response = given()
                .when()
                .delete("posts/1"); // Assuming deleting the first post
    }
}

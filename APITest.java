import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CoinDeskAPITest {
    public static void main(String[] args) {
        // Base URI of the CoinDesk API
        RestAssured.baseURI = "https://api.coindesk.com/v1/bpi/currentprice.json";

        // Send the GET request and get the response
        Response response = given().get();

        // Verify the response contains 3 BPIs: USD, GBP, EUR
        response.then().assertThat()
                .body("bpi.size()", is(3))
                .body("bpi.USD", notNullValue())
                .body("bpi.GBP", notNullValue())
                .body("bpi.EUR", notNullValue());

        // Verify the GBP 'description' equals 'British Pound Sterling'
        response.then().assertThat()
                .body("bpi.GBP.description", equalTo("British Pound Sterling"));

        // Print the response body for reference
        response.prettyPrint();
    }
}

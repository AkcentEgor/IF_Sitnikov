package api.requresApi;

import io.restassured.response.ValidatableResponse;
import models.requres.User;
import utils.ConfigReader;

import static io.restassured.RestAssured.given;

public class RequresApi extends BaseRequresApi {

        public ValidatableResponse postUser(User user) {
            return given()
                    .when()
                    .body(user)
                    .post(ConfigReader.getProperty("requres_endpoint_users"))
                    .then();
        }
}

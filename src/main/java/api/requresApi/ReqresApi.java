package api.requresApi;

import api.BaseApi;
import io.restassured.response.ValidatableResponse;
import models.reqres.User;
import utils.ConfigReader;

import static io.restassured.RestAssured.given;

public class ReqresApi extends BaseApi {

    public ReqresApi() {
        super(ConfigReader.getProperty("reqres.url"));
    }

        public ValidatableResponse postUser(User user) {
            return given()
                    .when()
                    .body(user)
                    .post(ConfigReader.getProperty("reqres.endpoint.users"))
                    .then();
        }
}

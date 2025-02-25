package api.requresApi;

import api.Specifications;
import io.restassured.RestAssured;
import utils.ConfigReader;

public class BaseRequresApi {
    public BaseRequresApi() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(ConfigReader.getProperty("requres_url"));
        RestAssured.responseSpecification = Specifications.baseResponseSpecSuccess();
    }
}

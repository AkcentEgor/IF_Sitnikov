package api;

import io.restassured.RestAssured;

public class BaseApi {
    public BaseApi(String url) {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(url);
        RestAssured.responseSpecification = Specifications.baseResponseSpecSuccess();
    }
}

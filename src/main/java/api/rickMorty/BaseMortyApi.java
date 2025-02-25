package api.rickMorty;

import api.Specifications;
import io.restassured.RestAssured;
import utils.ConfigReader;

public class BaseMortyApi {
    public BaseMortyApi() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(ConfigReader.getProperty("morty_url"));
        RestAssured.responseSpecification = Specifications.baseResponseSpecSuccess();
    }
}

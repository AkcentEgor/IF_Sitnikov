package api.rickMorty;

import api.BaseApi;
import io.restassured.response.ValidatableResponse;
import utils.ConfigReader;

import static io.restassured.RestAssured.given;

public class MortyApi extends BaseApi {

    public MortyApi() {
        super(ConfigReader.getProperty("morty.url"));
    }

    public ValidatableResponse getCharacterByNameResponse(String name) {
        return given()
                .when()
                .queryParam(ConfigReader.getProperty("morty.character.json.key.name"), name)
                .get(ConfigReader.getProperty("morty.endpoint.character"))
                .then();
    }

    public ValidatableResponse getResponseToEndpoint(int id, String mortyEndpoint) {
        return given()
                .when()
                .get(mortyEndpoint + "/" + id)
                .then();
    }
}

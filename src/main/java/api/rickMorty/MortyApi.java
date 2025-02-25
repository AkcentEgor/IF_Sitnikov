package api.rickMorty;

import io.restassured.response.ValidatableResponse;
import utils.ConfigReader;

import static io.restassured.RestAssured.given;

public class MortyApi extends BaseMortyApi {

    public ValidatableResponse getCharacterByNameResponse(String name) {
        return given()
                .when()
                .queryParam(ConfigReader.getProperty("morty_character_json_key_name"), name)
                .get(ConfigReader.getProperty("morty_endpoint_character"))
                .then();
    }

    public ValidatableResponse getEpisodeResponse(int id) {
        return given()
                .when()
                .get(ConfigReader.getProperty("morty_endpoint_episode") + "/" + id)
                .then();
    }

    public ValidatableResponse getCharacterByIdResponse(int id) {
        return given()
                .when()
                .get(ConfigReader.getProperty("morty_endpoint_character") + "/" + id)
                .then();
    }
}

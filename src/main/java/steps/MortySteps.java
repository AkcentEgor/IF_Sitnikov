package steps;

import api.rickMorty.MortyApi;
import models.morty.Character;
import models.morty.Episode;
import org.apache.http.HttpStatus;
import utils.ConfigReader;

import java.util.List;

public class MortySteps {

    private static final MortyApi mortyApi = new MortyApi();

    public Character getCharacterByName(String name) {
        List<Character> characters = mortyApi.getCharacterByNameResponse(name)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath()
                .getList(ConfigReader.getProperty("morty.json.get.list.path"), Character.class);
        return characters.get(0);
    }

    public int getLastEpisodeUrl(String name) {
        Character character = getCharacterByName(name);
        String lastEpisodeUrl = character.getEpisode().get(character.getEpisode().size() - 1);
        return Integer.parseInt(lastEpisodeUrl.substring(lastEpisodeUrl.lastIndexOf("/") + 1));
    }

    public int getLastCharacterInEpisode(int id, String mortyEndpoint) {
        Episode episode = mortyApi.getResponseToEndpoint(id, mortyEndpoint)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Episode.class);
        List<String> characters = episode.getCharacters();
        String lastCharactersUrl = characters.get(characters.size() - 1);
        return Integer.parseInt(lastCharactersUrl.substring(lastCharactersUrl.lastIndexOf("/") + 1));
    }

    public Character getCharacterById(int id, String mortyEndpoint) {
        return mortyApi.getResponseToEndpoint(id, mortyEndpoint)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Character.class);
    }
}

package steps;

import api.rickMorty.MortyApi;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Allure;
import models.morty.Character;
import models.morty.Episode;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import utils.ConfigReader;

import java.util.List;

public class MortySteps {

    private static final MortyApi mortyApi = new MortyApi();
    private int lastEpisodeId;
    private int lastCharactersId;
    private Character findCharacter;
    private Character lastCharacter;

    @Когда("Найти информацию по персонажу {string}")
    public Character getCharacterByName(String name) {
        List<Character> characters = mortyApi.getCharacterByNameResponse(name)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath()
                .getList(ConfigReader.getProperty("morty.json.get.list.path"), Character.class);
        findCharacter = characters.get(0);
        Allure.addAttachment("Персонаж с именем " + findCharacter.getName(), findCharacter.toString());
        return findCharacter;
    }

    @И("Получить из списка номер последнего эпизода, в котором появлялся персонаж")
    public int getLastEpisodeUrl() {
        String lastEpisodeUrl = findCharacter.getEpisode().get(findCharacter.getEpisode().size() - 1);
        lastEpisodeId = Integer.parseInt(lastEpisodeUrl.substring(lastEpisodeUrl.lastIndexOf("/") + 1));
        return lastEpisodeId;
    }

    @И("Получить последний эпизод по эндпоинту {string}")
    public int getLastCharacterInEpisode(String mortyEndpoint) {
        Episode episode = mortyApi.getResponseToEndpoint(lastEpisodeId, mortyEndpoint)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Episode.class);
        List<String> characters = episode.getCharacters();
        String lastCharactersUrl = characters.get(characters.size() - 1);
        lastCharactersId = Integer.parseInt(lastCharactersUrl.substring(lastCharactersUrl.lastIndexOf("/") + 1));
        return lastCharactersId;
    }

    @И("Получить из списка последнего эпизода последнего персонажа по эндпоинту {string}")
    public Character getCharacterById(String mortyEndpoint) {
        Character character = mortyApi.getResponseToEndpoint(lastCharactersId, mortyEndpoint)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Character.class);
        lastCharacter = character;
        Allure.addAttachment("Персонаж с именем " + lastCharacter.name, lastCharacter.toString());
        return  lastCharacter;
    }

    @Тогда("Проверить, что раса и местонахождение у искомого персонажа и последнего персонажа в последнем эпизоде, в котором появлялся искомый персонаж, не совпадают")
    public void checkSpeciesAndLocation() {
        Assertions.assertFalse(Boolean.parseBoolean(findCharacter.getSpecies()+findCharacter.getLocation()), lastCharacter.getSpecies()+lastCharacter.getLocation());
        Allure.addAttachment("Результаты данных для сравнения", String.format(
                "Персонаж: %s%nРаса: %s%nЛокация: %s%n%nПоследний персонаж в эпизоде:%nИмя: %s%nРаса: %s%nЛокация: %s",
                findCharacter.getName(), findCharacter.getSpecies(), findCharacter.getLocation(),
                lastCharacter.getName(), lastCharacter.getSpecies(), lastCharacter.getLocation()
        ));
    }
}

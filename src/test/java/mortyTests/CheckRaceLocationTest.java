package mortyTests;


import models.morty.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.MortySteps;
import utils.ConfigReader;

public class CheckRaceLocationTest {

    private static final MortySteps mortySteps = new MortySteps();

    @Test
    @DisplayName("Негативный тест на проверку соответствия расы и локации персонажей")
    public void checkRaceLocation() {
        Character morty = mortySteps.getCharacterByName(ConfigReader.getProperty("morty_name"));
        int lastEpisodeId = mortySteps.getLastEpisodeUrl(ConfigReader.getProperty("morty_name"));
        int lastCharacterId = mortySteps.getLastCharacterInEpisode(lastEpisodeId);
        Character lastCharacter = mortySteps.getCharacterById(lastCharacterId);

        Assertions.assertFalse(Boolean.parseBoolean(morty.getSpecies()+morty.getLocation()), lastCharacter.getSpecies()+lastCharacter.getLocation());
    }

}


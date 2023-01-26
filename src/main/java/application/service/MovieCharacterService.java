package application.service;

import application.model.MovieCharacter;
import java.util.List;

public interface MovieCharacterService {
    void syncExternalCharacters();

    MovieCharacter getRandomMovieCharacter();

    List<MovieCharacter> getAllByNameContains(String namePart);

    List<MovieCharacter> getAllByStatus(String status);

    List<MovieCharacter> getAllByGender(String gender);
}

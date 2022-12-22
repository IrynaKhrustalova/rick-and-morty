package iryna.khrustaleva.rick_and_morty_app.service;

import iryna.khrustaleva.rick_and_morty_app.model.MovieCharacter;

import java.util.List;

public interface MovieCharacterService {
    void syncExternalCharacters();

    MovieCharacter getRandomMovieCharacter();

    List<MovieCharacter> getAllByNameContains(String namePart);
}

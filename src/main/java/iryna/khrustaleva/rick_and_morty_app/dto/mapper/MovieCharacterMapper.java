package iryna.khrustaleva.rick_and_morty_app.dto.mapper;

import iryna.khrustaleva.rick_and_morty_app.dto.external.ApiCharacterDto;
import iryna.khrustaleva.rick_and_morty_app.dto.external.CharacterResponseDto;
import iryna.khrustaleva.rick_and_morty_app.model.Gender;
import iryna.khrustaleva.rick_and_morty_app.model.MovieCharacter;
import iryna.khrustaleva.rick_and_morty_app.model.Status;
import org.springframework.stereotype.Component;

@Component
public class MovieCharacterMapper {
    public MovieCharacter parseApiCharacterResponseDto(ApiCharacterDto apiCharacterDto) {
        MovieCharacter movieCharacter = new MovieCharacter();
        movieCharacter.setName(apiCharacterDto.getName());
        movieCharacter.setStatus(Status.valueOf(apiCharacterDto.getStatus().toUpperCase()));
        movieCharacter.setGender(Gender.valueOf(apiCharacterDto.getGender().toUpperCase()));
        movieCharacter.setExternalId(apiCharacterDto.getId());
        return movieCharacter;
    }

    public static CharacterResponseDto parseCharacterToResponseDto(MovieCharacter movieCharacter) {
        CharacterResponseDto characterResponseDto = new CharacterResponseDto();
        characterResponseDto.setId(movieCharacter.getId());
        characterResponseDto.setExternalId(movieCharacter.getExternalId());
        characterResponseDto.setName(movieCharacter.getName());
        characterResponseDto.setStatus(movieCharacter.getStatus().toString());
        characterResponseDto.setGender(movieCharacter.getGender().toString());
        return characterResponseDto;
    }
}

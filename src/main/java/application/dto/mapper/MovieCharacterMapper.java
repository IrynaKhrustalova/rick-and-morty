package application.dto.mapper;

import application.dto.external.ApiCharacterDto;
import application.dto.external.CharacterResponseDto;
import application.model.Gender;
import application.model.MovieCharacter;
import application.model.Status;
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

package iryna.khrustaleva.rick_and_morty_app.controller;

import java.util.List;
import java.util.stream.Collectors;
import iryna.khrustaleva.rick_and_morty_app.dto.external.CharacterResponseDto;
import iryna.khrustaleva.rick_and_morty_app.dto.mapper.MovieCharacterMapper;
import iryna.khrustaleva.rick_and_morty_app.model.MovieCharacter;
import iryna.khrustaleva.rick_and_morty_app.service.MovieCharacterService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie-characters")
public class MovieCharacterController {
    private final MovieCharacterService movieCharacterService;
    private final MovieCharacterMapper movieCharacterMapper;

    public MovieCharacterController(MovieCharacterService movieCharacterService, MovieCharacterMapper movieCharacterMapper) {
        this.movieCharacterService = movieCharacterService;
        this.movieCharacterMapper = movieCharacterMapper;
    }

    @GetMapping("/random")
    public CharacterResponseDto getRandomMovieCharacter() {
        MovieCharacter movieCharacter = movieCharacterService.getRandomMovieCharacter();
        return MovieCharacterMapper.parseCharacterToResponseDto(movieCharacter);
    }

    @GetMapping("/by-name")
    public List<CharacterResponseDto> getAllByNameContains(@RequestParam("name") String namePart) {
        return movieCharacterService.getAllByNameContains(namePart)
                .stream()
                .map(MovieCharacterMapper::parseCharacterToResponseDto)
                .collect(Collectors.toList());
    }
}

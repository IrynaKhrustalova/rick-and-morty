package application.controller;

import application.dto.external.CharacterResponseDto;
import application.dto.mapper.MovieCharacterMapper;
import application.model.MovieCharacter;
import application.service.MovieCharacterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-characters")
@Api("rick-and-morty-app")
public class MovieCharacterController {
    private final MovieCharacterService movieCharacterService;
    private final MovieCharacterMapper movieCharacterMapper;

    public MovieCharacterController(MovieCharacterService movieCharacterService,
                                    MovieCharacterMapper movieCharacterMapper) {
        this.movieCharacterService = movieCharacterService;
        this.movieCharacterMapper = movieCharacterMapper;
    }

    @GetMapping("/random")
    @ApiOperation(value = "Get random movie character")
    public CharacterResponseDto getRandomMovieCharacter() {
        MovieCharacter movieCharacter = movieCharacterService.getRandomMovieCharacter();
        return MovieCharacterMapper.parseCharacterToResponseDto(movieCharacter);
    }

    @GetMapping("/status")
    @ApiOperation(value = "Get all characters by status")
    public List<CharacterResponseDto> getAllByStatus(@RequestParam("status")
                                                         @ApiParam(value = "ALIVE, DEAD or UNKNOWN")
                                                         String status) {
        return movieCharacterService.getAllByStatus(status)
                .stream()
                .map(MovieCharacterMapper::parseCharacterToResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-name")
    @ApiOperation(value = "Get all characters contains name")
    public List<CharacterResponseDto> getAllByNameContains(@RequestParam("name") String namePart) {
        return movieCharacterService.getAllByNameContains(namePart)
                .stream()
                .map(MovieCharacterMapper::parseCharacterToResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/gender")
    @ApiOperation(value = "Get all characters by gender")
    public List<CharacterResponseDto> getAllByGender(@RequestParam("gender")
                                                         @ApiParam(value = "MALE, FEMALE, "
                                                                 + "GENDERLESS or UNKNOWN")
                                                                 String gender) {
        return movieCharacterService.getAllByGender(gender)
                .stream()
                .map(MovieCharacterMapper::parseCharacterToResponseDto)
                .collect(Collectors.toList());
    }
}

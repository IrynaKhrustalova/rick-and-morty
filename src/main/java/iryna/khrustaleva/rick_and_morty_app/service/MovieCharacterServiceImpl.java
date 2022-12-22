package iryna.khrustaleva.rick_and_morty_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import iryna.khrustaleva.rick_and_morty_app.dto.external.ApiCharacterDto;
import iryna.khrustaleva.rick_and_morty_app.dto.external.ApiResponseDto;
import iryna.khrustaleva.rick_and_morty_app.dto.mapper.MovieCharacterMapper;
import iryna.khrustaleva.rick_and_morty_app.model.MovieCharacter;
import iryna.khrustaleva.rick_and_morty_app.repository.MovieCharacterRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MovieCharacterServiceImpl implements MovieCharacterService {
    private final HttpClient httpClient;
    private final MovieCharacterRepository movieCharacterRepository;
    private final MovieCharacterMapper mapper;

    public MovieCharacterServiceImpl(HttpClient httpClient, MovieCharacterRepository movieCharacterRepository, MovieCharacterMapper mapper) {
        this.httpClient = httpClient;
        this.movieCharacterRepository = movieCharacterRepository;
        this.mapper = mapper;
    }

    @PostConstruct
    @Scheduled(cron = "* 8 * * * *")
    @Override
    public void syncExternalCharacters() {
        log.info("syncExternalCharacters method was called at " + LocalDateTime.now());
        ApiResponseDto apiResponseDto = httpClient.get("https://rickandmortyapi.com/api/character",
                ApiResponseDto.class);
       saveDtoToDb(apiResponseDto);

        while (apiResponseDto.getInfo().getNext() != null) {
            apiResponseDto = httpClient.get(apiResponseDto.getInfo().getNext(), ApiResponseDto.class);
            saveDtoToDb(apiResponseDto);
        }
    }

    @Override
    public MovieCharacter getRandomMovieCharacter() {
        long count = movieCharacterRepository.count();
        long randomId = (long) (Math.random() * count);
        return movieCharacterRepository.getReferenceById(randomId);
    }

    @Override
    public List<MovieCharacter> getAllByNameContains(String namePart) {
        return movieCharacterRepository.findAllByNameContains(namePart);
    }

    private void saveDtoToDb(ApiResponseDto apiResponseDto) {
        Map<Long, ApiCharacterDto> externalDtos = Arrays.stream(apiResponseDto.getResults())
                .collect(Collectors
                        .toMap(ApiCharacterDto::getId, Function.identity()));
        Set<Long> externalIds = externalDtos.keySet();
        List<MovieCharacter> existingCharacters = movieCharacterRepository
                .findAllByExternalIdIn(externalIds);
        Map<Long, MovieCharacter> existingCharactersWithIds = existingCharacters.stream()
                .collect(Collectors.toMap(MovieCharacter::getExternalId, Function.identity()));
        Set<Long> existingIds = existingCharactersWithIds.keySet();
        externalIds.removeAll(existingIds);
        List<MovieCharacter> charactersToSave = externalIds.stream().map(i -> mapper.parseApiCharacterResponseDto(externalDtos.get(i)))
                .collect(Collectors.toList());
        movieCharacterRepository.saveAll(charactersToSave);
    }
}

package iryna.khrustaleva.rick_and_morty_app.dto.external;

import iryna.khrustaleva.rick_and_morty_app.dto.external.ApiCharacterDto;
import iryna.khrustaleva.rick_and_morty_app.dto.external.ApiInfoDto;
import lombok.Data;

@Data
public class ApiResponseDto {
    private ApiInfoDto info;
    private ApiCharacterDto[] results;
}

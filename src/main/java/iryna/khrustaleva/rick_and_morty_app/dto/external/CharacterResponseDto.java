package iryna.khrustaleva.rick_and_morty_app.dto.external;

import lombok.Data;

@Data
public class CharacterResponseDto {
    private Long id;
    private Long externalId;
    private String name;
    private String status;
    private String gender;
}

package iryna.khrustaleva.rick_and_morty_app.dto.external;

import lombok.Data;

@Data
public class ApiInfoDto {
    private int count;
    private int pages;
    private String next;
    private String prev;
}

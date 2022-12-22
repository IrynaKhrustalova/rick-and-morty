package iryna.khrustaleva.rick_and_morty_app.repository;

import java.util.List;
import java.util.Set;
import iryna.khrustaleva.rick_and_morty_app.model.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Long> {
    List<MovieCharacter> findAllByExternalIdIn(Set<Long> externalIds);

    List<MovieCharacter> findAllByNameContains(String namePart);
}

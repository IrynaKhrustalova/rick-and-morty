package application.repository;

import application.model.Gender;
import application.model.MovieCharacter;
import application.model.Status;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Long> {
    List<MovieCharacter> findAllByExternalIdIn(Set<Long> externalIds);

    List<MovieCharacter> findAllByNameContains(String namePart);

    List<MovieCharacter> findAllByGender(Gender gender);

    List<MovieCharacter> findAllByStatus(Status status);
}

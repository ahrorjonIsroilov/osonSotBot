package osonsot.mainbot.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import osonsot.entity.auth.AuthUser;
import osonsot.entity.poster.Poster;

import java.util.List;

@Component
public interface PosterRepo extends JpaRepository<Poster, Long> {

    List<Poster> getAllByOwnerAndDeletedFalse(AuthUser owner, Pageable pageable);

    List<Poster> getAllByAcceptedAndRejected(Boolean accepted, Boolean rejected);
}

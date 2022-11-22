package osonsot.mainbot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import osonsot.entity.poster.Image;

import javax.transaction.Transactional;

@Component
public interface ImageRepo extends JpaRepository<Image, Long> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from image where poster_id=:posterId")
    void deleteByPoster(Long posterId);
}

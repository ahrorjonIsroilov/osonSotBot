package osonsot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import osonsot.entity.auth.AuthUser;

public interface AuthRepo extends JpaRepository<AuthUser, Long>, BaseRepo {
    Boolean existsByChatId(Long chatId);
}

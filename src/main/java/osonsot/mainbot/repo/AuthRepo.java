package osonsot.mainbot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osonsot.base.BaseRepo;
import osonsot.entity.auth.AuthUser;

@Repository
public interface AuthRepo extends JpaRepository<AuthUser, Long>, BaseRepo {

  Boolean existsByChatId(Long chatId);

  AuthUser getByChatId(Long chatId);
}

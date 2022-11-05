package osonsot.service;

import org.springframework.stereotype.Service;
import osonsot.repo.AuthRepo;

@Service
public class AuthService extends BaseService<AuthRepo> {
    public AuthService(AuthRepo repo) {
        super(repo);
    }

    public Boolean existsByCHatId(Long chatId) {
        return repo.existsByChatId(chatId);
    }
}

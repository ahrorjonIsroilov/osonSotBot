package osonsot.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import osonsot.entity.auth.AuthUser;
import osonsot.entity.poster.Category;
import osonsot.entity.poster.Poster;
import osonsot.mainbot.repo.AuthRepo;
import osonsot.mainbot.repo.CategoryRepo;
import osonsot.mainbot.repo.ImageRepo;
import osonsot.mainbot.repo.PosterRepo;

import java.util.List;

@Service
public record BotService(
        AuthRepo repo, CategoryRepo categoryRepo, ImageRepo imageRepo, PosterRepo posterRepo) {
    public Boolean existsByChatId(Long chatId) {
        return repo.existsByChatId(chatId);
    }

    public AuthUser getUserByChatId(Long chatId) {
        return repo.getByChatId(chatId);
    }

    public AuthUser getUserById(Long id) {
        return repo.getReferenceById(id);
    }

    public void saveUser(AuthUser user) {
        repo.save(user);
    }

    // CATEGORY==========================================

    public List<Category> getParentCategories() {
        return categoryRepo.getAllByIsSubcategoryFalseAndDeletedFalse();
    }

    public List<Category> getSubCategories(Integer parentId) {
        return categoryRepo.getAllByParentIdAndDeletedFalse(parentId);
    }

    public Category getCategory(Long id) {
        return categoryRepo.getReferenceById(id);
    }

    public void saveCategory(Category category) {
        categoryRepo.save(category);
    }

    public void deleteCategory(Long parent) {
        categoryRepo.deleteCategory(parent);
    }

    public boolean existSubCategories(Integer parent) {
        return categoryRepo.existsByParentId(parent);
    }
    // POSTER==========================================

    public void savePoster(Poster poster) {
        poster.getImages().forEach(image -> image.setPoster(poster));
        posterRepo.save(poster);
    }

    public Poster getMyPoster(Long chatId, Integer page) {
        Pageable p = PageRequest.of(page, 1);
        List<Poster> list = posterRepo.getAllByOwnerAndDeletedFalse(repo.getByChatId(chatId), p);
        if (!list.isEmpty()) return list.get(0);
        else return null;
    }

    public Poster getMyCurrentPoster(Long posterId) {
        return posterRepo.getReferenceById(posterId);
    }

    public void deletePoster(Long currentPosterId) {
        imageRepo.deleteByPoster(posterRepo.getReferenceById(currentPosterId).getId());
        posterRepo.deleteById(currentPosterId);
    }

    public List<Poster> getUnconfirmedPosters() {
        return posterRepo.getAllByAcceptedAndRejected(false, false);
    }

    public Poster getPosterById(Long posterId) {
        return posterRepo.getReferenceById(posterId);
    }

    public void deleteImageByPoster(Poster changedPoster) {
        imageRepo.deleteByPoster(changedPoster.getId());
    }
}

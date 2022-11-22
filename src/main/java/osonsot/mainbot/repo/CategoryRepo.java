package osonsot.mainbot.repo;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import osonsot.entity.poster.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

  List<Category> getAllByIsSubcategoryFalseAndDeletedFalse();

  List<Category> getAllByParentIdAndDeletedFalse(Integer parentId);

  @Modifying
  @Transactional
  @Query(nativeQuery = true, value = "delete from category c where c.id=:id or c.parent_id =:id")
  void deleteCategory(Long id);

  Boolean existsByParentId(Integer parentId);
}

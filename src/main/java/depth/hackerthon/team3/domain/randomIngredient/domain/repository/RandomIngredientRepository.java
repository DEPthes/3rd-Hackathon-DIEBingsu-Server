package depth.hackerthon.team3.domain.randomIngredient.domain.repository;

import depth.hackerthon.team3.domain.randomIngredient.domain.RandomIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RandomIngredientRepository extends JpaRepository<RandomIngredient, Long> {

    @Query(value = "SELECT * FROM random_ingredient ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<RandomIngredient> findRandomIngredients();
}

package depth.hackerthon.team3.domain.randomIngredient.domain.repository;

import depth.hackerthon.team3.domain.randomIngredient.domain.RandomIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RandomIngredientRepository extends JpaRepository<RandomIngredient, Long> {
}

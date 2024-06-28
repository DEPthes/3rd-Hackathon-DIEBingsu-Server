package depth.hackerthon.team3.domain.randomIngredient.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class RandomIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredientId; //재료아이디
    private String ingredientName; //재료이름

    @Builder
    public RandomIngredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }

}


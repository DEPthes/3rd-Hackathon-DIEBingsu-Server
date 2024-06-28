package depth.hackerthon.team3.domain.randomIngredient.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class RandomIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredient_id; //재료아이디
    private String ingredient_name; //재료이름

}


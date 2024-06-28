package depth.hackerthon.team3.domain.randomIngredient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RandomItemRes {

    private String item1;
    private String item2;
    private String item3;
}

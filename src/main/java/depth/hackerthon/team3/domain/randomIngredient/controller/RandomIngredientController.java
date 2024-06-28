package depth.hackerthon.team3.domain.randomIngredient.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ingredient")
@Tag(name = "Random Ingredient", description = "재료 관련 API입니다.")
public class RandomIngredientController {
}

package depth.hackerthon.team3.domain.randomIngredient.controller;

import depth.hackerthon.team3.domain.randomIngredient.service.RandomIngredientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ingredient")
@Tag(name = "Random Ingredient", description = "재료 관련 API입니다.")
public class RandomIngredientController {

    private final RandomIngredientService randomIngredientService;
    // 랜덤 재료 조회
    @GetMapping
    public ResponseEntity<?> getRandomIngredients() {
        return randomIngredientService.getRandomIngredients();
    }
}

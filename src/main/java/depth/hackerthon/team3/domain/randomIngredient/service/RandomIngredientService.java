package depth.hackerthon.team3.domain.randomIngredient.service;

import depth.hackerthon.team3.domain.randomIngredient.domain.RandomIngredient;
import depth.hackerthon.team3.domain.randomIngredient.domain.repository.RandomIngredientRepository;
import depth.hackerthon.team3.domain.randomIngredient.dto.RandomItemRes;
import depth.hackerthon.team3.global.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RandomIngredientService {

    private final RandomIngredientRepository randomIngredientRepository;

    public ResponseEntity<?> getRandomIngredients() {
        List<RandomIngredient> randomIngredients = randomIngredientRepository.findRandomIngredients();

        String item1 = randomIngredients.get(0).getIngredientName();
        String item2 = randomIngredients.get(1).getIngredientName();
        String item3 = randomIngredients.get(2).getIngredientName();

        RandomItemRes randomItemRes = RandomItemRes.builder()
                .item1(item1)
                .item2(item2)
                .item3(item3)
                .build();

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(randomItemRes)
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}

package depth.hackerthon.team3.domain.gpt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import depth.hackerthon.team3.global.config.GptConfig;
import depth.hackerthon.team3.global.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GptService {

    @Value("${chatgpt.api-key}")
    private String apiKey;

    private static final String DALL_E_URL = "https://api.openai.com/v1/images/generations";

    // DALL-E 이미지 요청 메서드 추가
    public JsonNode callDallE(String prompt) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("prompt", GptConfig.CONTENT + prompt);
        bodyMap.put("num_images", 1);

        String body = objectMapper.writeValueAsString(bodyMap);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(DALL_E_URL, HttpMethod.POST, request, String.class);

        return objectMapper.readTree(response.getBody());
    }

    public ResponseEntity<?> getImageFromDallE(String prompt) throws JsonProcessingException {
        JsonNode jsonNode = callDallE(prompt);
        String imageUrl = jsonNode.path("data").get(0).path("url").asText();

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(imageUrl)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}

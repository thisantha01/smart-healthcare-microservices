package com.healthcare.doctor_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIController {

    @Value("${gemini.api.key}")
    private String geminiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/symptom-checker")
    public ResponseEntity<String> checkSymptoms(@RequestBody Map<String, String> request) {
        String symptoms = request.get("symptoms");

        String prompt = "You are a helpful medical assistant. The patient said: '" + symptoms +
                "'. Reply with ONLY the recommended doctor specialty in one word (e.g. Cardiologist, Pediatrician, Dermatologist). Do not add any other text.";

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + geminiKey;

        // Simple JSON body for Gemini
        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(Map.of("text", prompt)))
                )
        );

        var response = restTemplate.postForObject(url, body, Map.class);
        // Extract the text (very simple parsing)
        String suggestion = response.get("candidates") != null ?
                ((Map)((List)response.get("candidates")).get(0)).get("content").toString() : "General Physician";

        return ResponseEntity.ok(suggestion);
    }
}
package com.locail.controller;

import com.locail.model.IngredientsRequest;
import com.locail.service.MealSuggestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MealSuggestionController {

    private final MealSuggestionService mealSuggestionService;

    public MealSuggestionController(MealSuggestionService mealSuggestionService) {
        this.mealSuggestionService = mealSuggestionService;
    }

    @Operation(summary = "Get meal suggestions based on available ingredients")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = IngredientsRequest.class),
            examples = @ExampleObject(
                value = """
                        {
                          "ingredients": [
                            "garlic",
                            "cucumber",
                            "tomato",
                            "feta cheese"
                          ]
                        }
                        """
            )
        )
    )
    @PostMapping("/suggestions")
    public ResponseEntity<Map<String, String>> getSuggestions(@RequestBody IngredientsRequest request) {
        String suggestion = mealSuggestionService.suggestMeals(request.ingredients());
        return ResponseEntity.ok(Map.of("suggestion", suggestion));
    }
}
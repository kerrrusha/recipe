package com.kerrrusha.recipe.controller;

import com.kerrrusha.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/show/{id}")
    public String showById(@PathVariable Long id, Model model){
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

}

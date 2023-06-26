package com.kerrrusha.recipe.controller;

import com.kerrrusha.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final RecipeService recipeService;

    @GetMapping({"", "/", "index", "index.html"})
    public String index(Model model) {
        log.info("GET request on index page");
        model.addAttribute("recipes", recipeService.findAll());
        return "index";
    }

}

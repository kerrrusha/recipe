package com.kerrrusha.recipe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class IndexController {

    @GetMapping({"", "/", "index", "index.html"})
    public String index() {
        log.info("GET request on index page");
        return "index";
    }

}

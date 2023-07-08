package com.kerrrusha.recipe.controller;

import com.kerrrusha.recipe.command.RecipeCommand;
import com.kerrrusha.recipe.service.ImageService;
import com.kerrrusha.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.kerrrusha.recipe.util.MapperUtil.toBytePrimitives;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    @GetMapping("/{id}/image-upload")
    public String showUploadForm(@PathVariable Long id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(id));
        return "recipe/image-upload-form";
    }

    @PostMapping("/{id}/image-upload")
    public String saveOrUpdate(@PathVariable Long id, @RequestParam("imagefile") MultipartFile file){
        imageService.saveImageFile(id, file);
        return "redirect:/recipe/" + id + "/show";
    }

    @GetMapping("/{id}/image-get")
    public void renderImageFromDB(@PathVariable Long id, HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.findCommandById(id);
        if (recipeCommand.getImage() != null) {
            byte[] byteArray = toBytePrimitives(recipeCommand.getImage());

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }

}

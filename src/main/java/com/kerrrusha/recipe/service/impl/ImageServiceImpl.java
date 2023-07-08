package com.kerrrusha.recipe.service.impl;

import com.kerrrusha.recipe.model.Recipe;
import com.kerrrusha.recipe.repository.RecipeRepository;
import com.kerrrusha.recipe.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static com.kerrrusha.recipe.util.MapperUtil.toByteObjects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {
        try {
            Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
            if (recipeOptional.isEmpty()) {
                throw new RuntimeException("#saveImageFile - Recipe was not found by id = " + recipeId);
            }
            Recipe recipe = recipeOptional.get();
            recipe.setImage(toByteObjects(file.getBytes()));
            recipeRepository.save(recipe);
        } catch (IOException e) {
            log.error("#saveImageFile for recipe.id="+recipeId+" - ", e);
        }
    }

}

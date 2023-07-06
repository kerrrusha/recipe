package com.kerrrusha.recipe.converter.category;


import com.kerrrusha.recipe.command.CategoryCommand;
import com.kerrrusha.recipe.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class CategoryCommandToCategoryConverter implements Converter<CategoryCommand, Category> {

    @Nullable
    @Override
    @Synchronized
    public Category convert(@Nullable CategoryCommand source) {
        if (isNull(source)) {
            return null;
        }

        final Category category = new Category();
        category.setId(source.getId());
        category.setName(source.getName());
        return category;
    }

}

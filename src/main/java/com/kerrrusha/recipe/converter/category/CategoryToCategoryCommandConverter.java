package com.kerrrusha.recipe.converter.category;

import com.kerrrusha.recipe.command.CategoryCommand;
import com.kerrrusha.recipe.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class CategoryToCategoryCommandConverter implements Converter<Category, CategoryCommand> {

    @Nullable
    @Override
    @Synchronized
    public CategoryCommand convert(@Nullable Category source) {
        if (isNull(source)) {
            return null;
        }

        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(source.getId());
        categoryCommand.setName(source.getName());
        return categoryCommand;
    }

}

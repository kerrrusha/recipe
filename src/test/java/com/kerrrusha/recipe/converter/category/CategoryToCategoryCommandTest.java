package com.kerrrusha.recipe.converter.category;

import com.kerrrusha.recipe.command.CategoryCommand;
import com.kerrrusha.recipe.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryToCategoryCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "descript";

    CategoryToCategoryCommandConverter converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommandConverter();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setName(DESCRIPTION);

        //when
        CategoryCommand categoryCommand = converter.convert(category);

        //then
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getName());
    }

}

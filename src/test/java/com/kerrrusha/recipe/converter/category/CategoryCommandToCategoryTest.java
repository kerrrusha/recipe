package com.kerrrusha.recipe.converter.category;

import com.kerrrusha.recipe.command.CategoryCommand;
import com.kerrrusha.recipe.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryCommandToCategoryTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";

    CategoryCommandToCategoryConverter converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategoryConverter();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setName(DESCRIPTION);

        //when
        Category category = converter.convert(categoryCommand);

        //then
        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getName());
    }

}

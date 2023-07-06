package com.kerrrusha.recipe.converter.uom;

import com.kerrrusha.recipe.command.UnitOfMeasureCommand;
import com.kerrrusha.recipe.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final String NAME = "description";
    public static final Long LONG_VALUE = 1L;

    UnitOfMeasureCommandToUnitOfMeasureConverter converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasureConverter();

    }

    @Test
    public void testNullParamter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(LONG_VALUE);
        command.setName(NAME);

        //when
        UnitOfMeasure uom = converter.convert(command);

        //then
        assertNotNull(uom);
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(NAME, uom.getName());

    }

}

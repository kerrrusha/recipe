package com.kerrrusha.recipe.converter.uom;

import com.kerrrusha.recipe.command.UnitOfMeasureCommand;
import com.kerrrusha.recipe.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final String NAME = "description";
    public static final Long LONG_VALUE = 1L;

    UnitOfMeasureToUnitOfMeasureCommandConverter converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommandConverter();
    }

    @Test
    public void testNullObjectConvert() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObj() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() throws Exception {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(LONG_VALUE);
        uom.setName(NAME);

        //when
        UnitOfMeasureCommand uomc = converter.convert(uom);

        //then
        assertEquals(LONG_VALUE, uomc.getId());
        assertEquals(NAME, uomc.getName());
    }

}

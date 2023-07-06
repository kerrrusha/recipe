package com.kerrrusha.recipe.converter.notes;

import com.kerrrusha.recipe.command.NotesCommand;
import com.kerrrusha.recipe.model.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotesToNotesCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String CONTENT = "Notes";

    NotesToNotesCommandConverter converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new NotesToNotesCommandConverter();
    }

    @Test
    public void convert() throws Exception {
        //given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setContent(CONTENT);

        //when
        NotesCommand notesCommand = converter.convert(notes);

        //then
        assertEquals(ID_VALUE, notesCommand.getId());
        assertEquals(CONTENT, notesCommand.getContent());
    }

    @Test
    public void testNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

}

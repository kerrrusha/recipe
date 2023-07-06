package com.kerrrusha.recipe.converter.notes;

import com.kerrrusha.recipe.command.NotesCommand;
import com.kerrrusha.recipe.model.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotesCommandToNotesTest {

    public static final Long ID_VALUE = 1L;
    public static final String CONTENT = "Notes";

    NotesCommandToNotesConverter converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new NotesCommandToNotesConverter();

    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID_VALUE);
        notesCommand.setContent(CONTENT);

        //when
        Notes notes = converter.convert(notesCommand);

        //then
        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(CONTENT, notes.getContent());
    }

}
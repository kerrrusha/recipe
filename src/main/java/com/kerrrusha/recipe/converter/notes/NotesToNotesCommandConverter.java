package com.kerrrusha.recipe.converter.notes;

import com.kerrrusha.recipe.command.NotesCommand;
import com.kerrrusha.recipe.model.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommandConverter implements Converter<Notes, NotesCommand> {

    @Nullable
    @Override
    @Synchronized
    public NotesCommand convert(@Nullable Notes source) {
        if (source == null) {
            return null;
        }

        final NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(source.getId());
        notesCommand.setContent(source.getContent());
        return notesCommand;
    }

}

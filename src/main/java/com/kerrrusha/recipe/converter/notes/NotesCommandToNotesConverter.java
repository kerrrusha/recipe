package com.kerrrusha.recipe.converter.notes;

import com.kerrrusha.recipe.command.NotesCommand;
import com.kerrrusha.recipe.model.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotesConverter implements Converter<NotesCommand, Notes> {

    @Nullable
    @Override
    @Synchronized
    public Notes convert(@Nullable NotesCommand source) {
        if(source == null) {
            return null;
        }

        final Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setContent(source.getContent());
        return notes;
    }

}

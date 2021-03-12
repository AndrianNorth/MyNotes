package ru.geekbrains.mynotes;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class NoteItemCallback extends DiffUtil.ItemCallback<SimpleNotes> {

    @Override
    public boolean areItemsTheSame(@NonNull SimpleNotes oldItem, @NonNull SimpleNotes newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull SimpleNotes oldItem, @NonNull SimpleNotes newItem) {
        return oldItem.getTitle().equals(newItem.getTitle()) &&
                oldItem.getDescription().equals(newItem.getDescription());
    }
}

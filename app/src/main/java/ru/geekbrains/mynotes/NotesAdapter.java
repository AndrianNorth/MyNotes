package ru.geekbrains.mynotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

public class NotesAdapter extends ListAdapter<SimpleNotes, NotesAdapter.NotesViewHolder> {

    private final NotesAdapterCallbacks callbacks;

    public NotesAdapter(
            @NonNull DiffUtil.ItemCallback<SimpleNotes> diffCallback,
            @NonNull NotesAdapterCallbacks callbacks) {
        super(diffCallback);
        this.callbacks = callbacks;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.onBind(getItem(position), position);
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        private final MaterialTextView textView;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item_title);
        }

        public void onBind(SimpleNotes model, int position) {
            textView.setText(model.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callbacks.onItemClicked(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    callbacks.onLongItemClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}

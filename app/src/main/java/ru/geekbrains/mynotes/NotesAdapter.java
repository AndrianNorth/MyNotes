package ru.geekbrains.mynotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private static final String TAG = "NotesAdapter";

    private final List<SimpleNotes> simpleNotes = new ArrayList<>();

    public void setItems(List<SimpleNotes> items){
        simpleNotes.clear();
        simpleNotes.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.onBind(simpleNotes.get(position), position);
    }

    @Override
    public int getItemCount() {
        return simpleNotes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        private final MaterialTextView textView;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item_title);
        }

        public void onBind(SimpleNotes model, int position){
            textView.setText(model.getTITLE());
        }
    }
}

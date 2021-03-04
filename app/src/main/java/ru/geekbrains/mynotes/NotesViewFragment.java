package ru.geekbrains.mynotes;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesViewFragment extends Fragment implements NotesAdapterCallbacks {

    private List<SimpleNotes> notes = new ArrayList<>();
    private final NotesAdapter notesAdapter = new NotesAdapter((NotesAdapterCallbacks) this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initArraylist();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notes_view_fragment, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        notesAdapter.setItems(notes);
    }

    @Override
    public void onItemClicked(int position) {

    }

    private void replaceFragment(@NonNull SimpleNotes model){

    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rv_notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(notesAdapter);
        }


    private void initArraylist() {
        notes.add(new SimpleNotes("Список покупок", "Продукты", "18.02.2020"));
        notes.add(new SimpleNotes("Посмотреть фильм", "Побег из Претории", "21.02.2020"));
        notes.add(new SimpleNotes("Повторить коллекции", "Serializable", "18.02.2020"));
        notes.add(new SimpleNotes("Прочитать методичку", "К следующему уроку", "19.02.2020"));
    }
}

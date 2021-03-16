package ru.geekbrains.mynotes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NotesViewFragment extends Fragment implements NotesAdapterCallbacks {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;

    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private final List<SimpleNotes> notes = new ArrayList<>();
    private final NotesAdapter notesAdapter = new NotesAdapter(new NoteItemCallback(), this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        recyclerView = view.findViewById(R.id.rv_notes);
        floatingActionButton = view.findViewById(R.id.fb_notes_add);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setAdapter(notesAdapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(null);
            }
        });
        getNotes();
    }

    @Override
    public void onItemClicked(int position) {
        SimpleNotes model = notes.get(position);
        MainActivity.idOnDelete = String.valueOf(notes.get(position).getId());
        replaceFragment(model);
    }

    private void replaceFragment(@Nullable SimpleNotes model) {
        Fragment fragment = NotesEditFragment.newInstance(model);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void getNotes() {
        firebaseFirestore
                .collection("notes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.getResult() != null) {
                            List<SimpleNotes> items = task.getResult().toObjects(SimpleNotes.class);
                            notes.clear();
                            notes.addAll(items);
                            notesAdapter.submitList(items);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(requireContext(), "ошибка загрузки текста", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

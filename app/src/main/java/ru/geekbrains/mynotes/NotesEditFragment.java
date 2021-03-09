package ru.geekbrains.mynotes;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NotesEditFragment extends Fragment {
    public static final String ARG_INDEX = "ARG";

    private EditText titleEditText;
    private EditText descriptionEditText;

    public static Fragment newInstance(@NonNull SimpleNotes model){
        Fragment fragment = new NotesEditFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_INDEX, model);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleEditText = view.findViewById(R.id.et_notes_title);
        descriptionEditText = view.findViewById(R.id.et_notes_description);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getArguments() != null){
            SimpleNotes simpleNotes = (SimpleNotes) getArguments().getSerializable(ARG_INDEX);
            titleEditText.setText(simpleNotes.getTITLE());
            descriptionEditText.setText(simpleNotes.getDESCRIPTION());
        }
    }
}

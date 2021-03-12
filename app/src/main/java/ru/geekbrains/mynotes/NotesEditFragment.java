package ru.geekbrains.mynotes;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NotesEditFragment extends Fragment {
    public static final String ARG_INDEX = "ARG";
    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public static Fragment newInstance(@Nullable SimpleNotes model) {
        Fragment fragment = new NotesEditFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_INDEX, model);
        fragment.setArguments(bundle);
        return fragment;
    }

    private EditText titleEditText;
    private EditText descriptionEditText;
    private Button saveButton;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.notes_edit_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleEditText = view.findViewById(R.id.et_notes_title);
        descriptionEditText = view.findViewById(R.id.et_notes_description);
        saveButton = view.findViewById(R.id.btn_notes_edit_save);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String title = titleEditText.getText().toString();
                final String description = descriptionEditText.getText().toString();
                saveToDataBase(title, description);
            }
        });
        if (getArguments() != null) {
            SimpleNotes simpleNotes = (SimpleNotes) getArguments().getSerializable(ARG_INDEX);
            if (simpleNotes != null) {
                titleEditText.setText(simpleNotes.getTitle());
                descriptionEditText.setText(simpleNotes.getDescription());
            }
        }
    }

    private void saveToDataBase(
            @Nullable String title,
            @Nullable String description) {
        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description)) {
            final String id = UUID.randomUUID().toString();
            final Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("title", title);
            map.put("description", description);
            firebaseFirestore.collection(Constants.TABLE_NAME_NOTES)
                    .document(id)
                    .set(map)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        } else {
            Toast.makeText(requireContext(), "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show();
        }
    }
}

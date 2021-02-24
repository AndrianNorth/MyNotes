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
import java.util.ArrayList;
import java.util.List;

public class NotesViewFragment extends Fragment {

    private List<SimpleNotes> notes = new ArrayList<>();
    private boolean isLandscapeOrientation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initArraylist();
        isLandscapeOrientation =
                getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
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

    private void initView(View view) {
        LinearLayout linearLayout = (LinearLayout) view;

        for (SimpleNotes note : notes) {
            TextView textView = new TextView(linearLayout.getContext());
            textView.setText(note.getTITLE() + " " + note.getDESCRIPTION() + " " + note.getDATE());
            textView.setPadding(10, 0, 10, 0);
            textView.setTextSize(30f);
            linearLayout.addView(textView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkOrientation();
                }
            });
        }
    }

    private void checkOrientation() {
        if (isLandscapeOrientation) {
            openNoteFragment();
        } else {
            startNoteActivity();
        }
    }

    private void openNoteFragment() {
        NotesEditFragment fragment = new NotesEditFragment();
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.NotesEdit, fragment)
                .commit();
    }

    private void startNoteActivity() {
        Intent intent = new Intent(getActivity(), NotesEditActivity.class);
        startActivity(intent);
    }

    private void initArraylist() {
        notes.add(new SimpleNotes("Список покупок", "Продукты", "18.02.2020"));
        notes.add(new SimpleNotes("Посмотреть фильм", "Побег из Претории", "21.02.2020"));
        notes.add(new SimpleNotes("Повторить коллекции", "Serializable", "18.02.2020"));
        notes.add(new SimpleNotes("Прочитать методичку", "К следующему уроку", "19.02.2020"));
    }
}

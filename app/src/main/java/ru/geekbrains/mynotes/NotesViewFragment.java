package ru.geekbrains.mynotes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class NotesViewFragment extends Fragment {

    private List<SimpleNotes> notes = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initArraylist();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.notes_view_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        int padding = getResources().getDimensionPixelSize(R.dimen.default_margin);

        for (SimpleNotes note : notes) {
            TextView textView = new TextView(linearLayout.getContext());
            textView.setText(note.getTITLE() + " " + note.getDESCRIPTION() + " " + note.getDATE());
            textView.setPadding(padding, 0, padding, 0);
            textView.setTextSize(30f);
            linearLayout.addView(textView);
        }
    }

    private void initArraylist() {
        notes.add(new SimpleNotes("Список покупок", "Продукты", "18.02.2020"));
        notes.add(new SimpleNotes("Посмотреть фильм", "Побег из Претории", "21.02.2020"));
        notes.add(new SimpleNotes("Повторить коллекции", "Serializable", "18.02.2020"));
        notes.add(new SimpleNotes("Прочитать методичку", "К следующему уроку", "19.02.2020"));
    }
}

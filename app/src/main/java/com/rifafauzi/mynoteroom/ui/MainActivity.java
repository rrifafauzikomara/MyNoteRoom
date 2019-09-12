package com.rifafauzi.mynoteroom.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.rifafauzi.mynoteroom.R;
import com.rifafauzi.mynoteroom.adapter.NoteAdapter;
import com.rifafauzi.mynoteroom.database.Note;
import com.rifafauzi.mynoteroom.viewmodel.MainViewModel;
import com.rifafauzi.mynoteroom.viewmodel.ViewModelFactory;

import java.util.List;

import static com.rifafauzi.mynoteroom.ui.NoteAddUpdateActivity.REQUEST_UPDATE;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainViewModel mainViewModel = obtainViewModel(MainActivity.this);
        mainViewModel.getAllNotes().observe(this, noteObserver);

        adapter = new NoteAdapter(MainActivity.this);

        recyclerView = findViewById(R.id.rv_notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.fab_add) {
                    Intent intent = new Intent(MainActivity.this, NoteAddUpdateActivity.class);
                    startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_ADD);
                }
            }
        });
    }

    private final Observer<List<Note>> noteObserver = new Observer<List<Note>>() {
        @Override
        public void onChanged(@Nullable List<Note> noteList) {
            if (noteList != null) {
                adapter.setListNotes(noteList);
            }
        }
    };

    @NonNull
    private static MainViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(MainViewModel.class);
    }

    private void showSnackbarMessage(String message) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == NoteAddUpdateActivity.REQUEST_ADD) {
                if (resultCode == NoteAddUpdateActivity.RESULT_ADD) {
                    showSnackbarMessage(getString(R.string.added));
                }
            } else if (requestCode == REQUEST_UPDATE) {
                if (resultCode == NoteAddUpdateActivity.RESULT_UPDATE) {
                    showSnackbarMessage(getString(R.string.changed));
                } else if (resultCode == NoteAddUpdateActivity.RESULT_DELETE) {
                    showSnackbarMessage(getString(R.string.deleted));
                }
            }
        }
    }

}
package com.rifafauzi.mynoteroom.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.rifafauzi.mynoteroom.database.Note;
import com.rifafauzi.mynoteroom.database.NoteDao;
import com.rifafauzi.mynoteroom.database.NoteDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {

    private NoteDao mNotesDao;
    private ExecutorService executorService;
    public NoteRepository(Application application) {
        executorService = Executors.newSingleThreadExecutor();

        NoteDatabase db = NoteDatabase.getDatabase(application);
        mNotesDao = db.noteDao();
    }

    public LiveData<List<Note>> getAllNotes() {
        return mNotesDao.getAllNotes();
    }

    public void insert(final Note note) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mNotesDao.insert(note);
            }
        });
    }

    public void delete(final Note note){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mNotesDao.delete(note);
            }
        });
    }

    public void update(final Note note){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mNotesDao.update(note);
            }
        });
    }
}
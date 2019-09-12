package com.rifafauzi.mynoteroom.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.rifafauzi.mynoteroom.database.Note;
import com.rifafauzi.mynoteroom.repository.NoteRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private NoteRepository mNoteRepository;

    public MainViewModel(Application application) {
        mNoteRepository = new NoteRepository(application);
    }

    public LiveData<List<Note>> getAllNotes() {
        return mNoteRepository.getAllNotes();
    }
}
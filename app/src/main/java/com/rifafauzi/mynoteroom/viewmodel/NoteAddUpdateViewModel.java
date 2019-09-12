package com.rifafauzi.mynoteroom.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.rifafauzi.mynoteroom.database.Note;
import com.rifafauzi.mynoteroom.repository.NoteRepository;

public class NoteAddUpdateViewModel extends ViewModel {

    private NoteRepository mNoteRepository;

    public NoteAddUpdateViewModel(Application application) {
        mNoteRepository = new NoteRepository(application);
    }

    public void insert(Note note) {
        mNoteRepository.insert(note);
    }

    public void update(Note note) {
        mNoteRepository.update(note);
    }

    public void delete(Note note) {
        mNoteRepository.delete(note);
    }

}

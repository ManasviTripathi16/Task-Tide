package com.manasvi.tasktide;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepo {

    private NoteDao noteDao;
    private LiveData<List<Note>> notelist;



    public NoteRepo(Application application)
    {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        notelist = noteDao.getAllData();

    }

    public void insertData (Note note){ new insertTask(noteDao).execute(note);}
    public void updateData (Note note){new updateTask(noteDao).execute(note);}
    public void deleteData (Note note){new DeleteTask(noteDao).execute(note);}
    public LiveData<List<Note>> getAllData(){
        return notelist;
    };

    private static class insertTask extends AsyncTask<Note , Void , Void>{
        private NoteDao noteDao;
        public insertTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<Note , Void , Void>{
        private NoteDao noteDao;
        public DeleteTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class updateTask extends AsyncTask<Note , Void , Void>{
        private NoteDao noteDao;
        public updateTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }


}

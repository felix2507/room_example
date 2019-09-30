package de.fschlosser.room_datenbank;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AuflistungActivity extends AppCompatActivity {

    private WordDao dao;
    private RecyclerView recyclerView;
    private WordListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auflistung);

        recyclerView = findViewById(R.id.word_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WordListAdapter();
        recyclerView.setAdapter(adapter);

        dao = WordRoomDatabase.getDatabase(this).wordDao();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new LadeWordsTask().execute();
    }


    class LadeWordsTask extends AsyncTask<Void, Void, List<Word>>{

        @Override
        protected List<Word> doInBackground(Void... voids) {
            return dao.getAll();
        }

        @Override
        protected void onPostExecute(List<Word> words) {
            super.onPostExecute(words);
            adapter.setWords(words);
        }
    }
}

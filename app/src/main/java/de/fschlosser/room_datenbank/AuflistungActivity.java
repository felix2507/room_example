package de.fschlosser.room_datenbank;

import android.os.AsyncTask;
import android.os.Bundle;

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

        dao = WordRoomDatabase.getDatabase(this).wordDao();

        recyclerView = findViewById(R.id.word_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new WordListAdapter(dao);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        new LadeWordsTask(dao, adapter).execute();
    }


    static class LadeWordsTask extends AsyncTask<Void, Void, List<Word>>{

        private final WordDao dao;
        private final WordListAdapter adapter;

        public LadeWordsTask(WordDao dao, WordListAdapter adapter) {
            this.dao = dao;
            this.adapter = adapter;
        }

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

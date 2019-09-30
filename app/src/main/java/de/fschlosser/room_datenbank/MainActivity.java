package de.fschlosser.room_datenbank;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WordDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = WordRoomDatabase.getDatabase(this).wordDao();

        Button buttonSpeichern = findViewById(R.id.button_speichern);
        buttonSpeichern.setOnClickListener(view -> {
                        saveWordOnClick();
        });

        Button buttonZeigeListe = findViewById(R.id.button_zeige_liste);
        buttonZeigeListe.setOnClickListener((view) ->{
            Intent intent = new Intent(this, AuflistungActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void saveWordOnClick() {
        EditText wort = findViewById(R.id.word_entry);
        if (!wort.getText().toString().isEmpty()) {
            new SpeichernTask()
                    .execute(new Word(wort.getText().toString()));
        }
    }


    class SpeichernTask extends AsyncTask<Word, Void, Void>{

        @Override
        protected Void doInBackground(Word... words) {
            dao.insert(words[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}

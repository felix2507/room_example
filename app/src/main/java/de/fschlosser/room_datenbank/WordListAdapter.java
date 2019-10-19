package de.fschlosser.room_datenbank;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

class WordViewHolder extends RecyclerView.ViewHolder {
    WordViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}

public class WordListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Word> words = Collections.emptyList();
    private final WordDao dao;

    public WordListAdapter(WordDao dao) {
        this.dao = dao;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder
            (@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.word_list_item, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder
            (@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView wordView = holder.itemView.findViewById(R.id.list_item_word);
        wordView.setText(words.get(position).getWord());
        wordView.setOnClickListener((view) -> {
            new DeleteWordTask(dao, this).execute(words.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public void setWords(List<Word> words) {
        this.words = words;
        notifyDataSetChanged();
    }

    static class DeleteWordTask extends AsyncTask<Word, Void, List<Word>> {

        private final WordDao dao;
        private final WordListAdapter adapter;

        public DeleteWordTask(WordDao dao, WordListAdapter adapter) {
            this.dao = dao;
            this.adapter = adapter;
        }

        @Override
        protected List<Word> doInBackground(Word... words) {
            dao.delete(words[0]);
            return dao.getAll();
        }

        @Override
        protected void onPostExecute(List<Word> words) {
            super.onPostExecute(words);
            adapter.setWords(words);
        }
    }


}

package de.fschlosser.room_datenbank;

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

    WordListAdapter() {
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
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public void setWords(List<Word> words){
        this.words = words;
        notifyDataSetChanged();
    }
}

package de.fschlosser.room_datenbank;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Word word);

    @Query("SELECT * from Word")
    public List<Word> getAll();

    @Delete
    public void delete(Word word);
}

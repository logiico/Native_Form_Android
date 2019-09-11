package net.logiico.formnativeandroidjava.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface BaseDao<T> {

    @Insert(onConflict = REPLACE)
    long insert(T obj);

    @Insert(onConflict = REPLACE)
    void insertAll(List<T> obj);

    @Delete
    void delete(T obj);
}

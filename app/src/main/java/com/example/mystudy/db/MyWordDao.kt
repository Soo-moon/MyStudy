package com.example.mystudy.db

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao

interface MyWordDao {
    @Query("SELECT * FROM MyWord")
    fun getAll(): List<MyWord>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: MyWord)

    @Delete
    fun delete(obj: MyWord)
}
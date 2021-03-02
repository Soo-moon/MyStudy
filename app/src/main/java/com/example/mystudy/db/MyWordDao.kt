package com.example.mystudy.db

import androidx.room.*


@Dao

interface MyWordDao {
    @Query("SELECT * FROM MyWord")
    fun getAll(): List<MyWord>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: MyWord)

    @Query("SELECT * FROM MyWord WHERE ID = :index")
    fun getData(index: Int): MyWord

    @Delete
    fun delete(obj: MyWord)
}
package com.example.mystudy.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {

    

    @Query("SELECT * FROM BaseWord")
    fun getAll(): LiveData<List<BaseWord>>

    @Insert
    fun insert(obj : BaseWord)

    @Query("SELECT * FROM BASEWORD WHERE ID = :index")
    fun getData(index : Int): LiveData<BaseWord>

    @Delete
    fun delete(obj: BaseWord)
}

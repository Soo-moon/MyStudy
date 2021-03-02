package com.example.mystudy.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface WordDao {

    @Query("SELECT * FROM BaseWord")
    fun getAll(): List<BaseWord>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj : BaseWord)

    @Query("SELECT * FROM BaseWord WHERE ID = :index")
    fun getData(index : Int): BaseWord

}

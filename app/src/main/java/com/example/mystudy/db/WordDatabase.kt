package com.example.mystudy.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BaseWord::class, MyWord::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {

    var dbVersion : Int = 0


    abstract fun wordDao(): WordDao
    abstract fun myWordDao() : MyWordDao


    private var dbVersion : Float = 0.0F
    fun getVersion() : Float = dbVersion
    fun setVersion(version : Float ) {
        dbVersion = version
    }


    companion object {
        private var INSTANCE: WordDatabase? = null

        fun getInstance(context: Context): WordDatabase? {
            if (INSTANCE == null) {
                synchronized(WordDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        WordDatabase::class.java, "Word_database"
                    )
                        .build()
                }

            }
            return INSTANCE
        }


    }

    fun getVersion() : Int = dbVersion

}
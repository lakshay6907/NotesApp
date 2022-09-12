package com.example.notesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase(): RoomDatabase() {

     abstract fun notesDao(): NotesDao

     companion object {

          @Volatile
          private var INSTANCE: NotesDatabase? = null

          fun getDatabase(context: Context): NotesDatabase {
               return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                         context.applicationContext,
                         NotesDatabase::class.java,
                         "notes_database"
                    ).build()
                    INSTANCE = instance
                    // return instance
                    return instance
               }
          }
     }
}
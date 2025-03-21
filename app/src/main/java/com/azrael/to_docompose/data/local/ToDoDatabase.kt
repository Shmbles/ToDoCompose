package com.azrael.to_docompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.azrael.to_docompose.data.model.ToDoTask

@Database(entities = [ToDoTask::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}
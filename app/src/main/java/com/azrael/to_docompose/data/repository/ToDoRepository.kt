package com.azrael.to_docompose.data.repository

import com.azrael.to_docompose.data.local.ToDoDao
import com.azrael.to_docompose.data.model.ToDoTask
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ToDoRepository @Inject constructor(private val toDoDao: ToDoDao) {
    val getAllTasks: Flow<List<ToDoTask>> = toDoDao.getAllTask()
    val sortByLowPriority: Flow<List<ToDoTask>> = toDoDao.sortByLowPriority()
    val sortByHighPriority: Flow<List<ToDoTask>> = toDoDao.sortByHighPriority()

    fun getSelectedTask(taskId: Int): Flow<ToDoTask> {
        return toDoDao.getSelectedTask(taskId)
    }

    suspend fun addTask(todoTask: ToDoTask) {
        return toDoDao.addTask(todoTask)
    }

    suspend fun updateTask(todoTask: ToDoTask) {
        return toDoDao.updateTask(todoTask)
    }

    suspend fun deleteTask(todoTask: ToDoTask) {
        return toDoDao.deleteTask(todoTask)
    }

    suspend fun deleteAllTask() {
        return toDoDao.deleteAllTasks()
    }

    fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>> {
        return toDoDao.searchDatabase(searchQuery)
    }
}
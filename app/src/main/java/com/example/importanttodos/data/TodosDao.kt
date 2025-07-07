package com.example.importanttodos.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodosDao {

    @Insert
    fun insert(todo: Todo)

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)

    @Query("SELECT * FROM todos_table WHERE id = :todoId")
    fun get(todoId: Long): LiveData<Todo>

    @Query("SELECT * FROM todos_table ORDER BY id DESC")
    fun getAll(): LiveData<List<Todo>>
}

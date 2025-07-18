package com.example.importanttodos.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.importanttodos.data.Todo
import com.example.importanttodos.data.TodosDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodosViewModel(val dao: TodosDao): ViewModel() {
    var newTodoTitle = ""
    val todos = dao.getAll()
    private val _navigateToTodo = MutableLiveData<Long?>()
    val navigateToTodo: LiveData<Long?> get() = _navigateToTodo

    fun addTodo() {
        viewModelScope.launch(Dispatchers.IO) {
            val todo = Todo()
            todo.title = newTodoTitle
            dao.insert(todo)
        }
    }

    fun onTodoItemClicked(todoId: Long) {
        _navigateToTodo.value = todoId
    }

    fun onTodoItemNavigated() {
        _navigateToTodo.value = null
    }
}

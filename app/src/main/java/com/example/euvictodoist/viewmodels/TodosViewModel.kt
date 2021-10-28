package com.example.euvictodoist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.euvictodoist.adapters.TodosAdapter
import com.example.euvictodoist.base.BaseViewModel
import com.example.euvictodoist.models.TodoResponse
import com.example.euvictodoist.network.repository.TodoRepository
import kotlinx.coroutines.launch
import java.util.*

class TodosViewModel(private val repository: TodoRepository) : BaseViewModel() {

    private val _todoList = MutableLiveData<ArrayList<TodoResponse>>()
    val todoList : LiveData<ArrayList<TodoResponse>> = _todoList

    fun requestTodosList() {
        viewModelScope.launch {
            _todoList.postValue(repository.getTodosList())
        }
    }
}
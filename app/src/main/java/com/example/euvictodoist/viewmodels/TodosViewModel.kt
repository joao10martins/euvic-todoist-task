package com.example.euvictodoist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.euvictodoist.adapters.TodosAdapter
import com.example.euvictodoist.base.BaseViewModel
import com.example.euvictodoist.models.TodoResponse
import com.example.euvictodoist.network.repository.TodoRepository
import kotlinx.coroutines.launch
import java.util.*

class TodosViewModel(private val repository: TodoRepository) : BaseViewModel() {

    var adapter = TodosAdapter()
    var todoList = MutableLiveData<ArrayList<TodoResponse>>()

    fun requestTodosList() {
        viewModelScope.launch {
            todoList.postValue(repository.getTodosList())
        }
    }

    fun setList(list : List<TodoResponse>) {
        adapter.setData(list)
    }

    fun getAdapterTodoList() : TodosAdapter {
        return adapter
    }
}
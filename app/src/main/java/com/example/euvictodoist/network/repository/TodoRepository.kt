package com.example.euvictodoist.network.repository

import com.example.euvictodoist.models.TodoResponse
import com.example.euvictodoist.network.ApiClient
import com.example.euvictodoist.network.services.TodoService
import java.util.*

class TodoRepository(api : ApiClient) {

    private var todoService : TodoService = api.getApiClient().create(TodoService::class.java)

    suspend fun getTodosList() : ArrayList<TodoResponse> =
        runCatching {
            todoService.getTodosList()
        }.getOrElse {
            throw Exception(it.message)
        }
}
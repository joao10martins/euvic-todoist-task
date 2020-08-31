package com.example.euvictodoist.network.services

import com.example.euvictodoist.models.TodoResponse
import retrofit2.http.GET
import java.util.*

interface TodoService {
    // Note: the 'suspend' keyword can be only be called from within a Coroutine, otherwise we'll get a compiler error.

    @GET("todos")
    suspend fun getTodosList() : ArrayList<TodoResponse>
}
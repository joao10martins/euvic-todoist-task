package com.example.euvictodoist.di

import com.example.euvictodoist.network.services.PostService
import com.example.euvictodoist.network.services.TodoService
import org.koin.dsl.module
import retrofit2.Retrofit

var serviceModule = module {
    single { get<Retrofit>().create(TodoService::class.java) }
    single { get<Retrofit>().create(PostService::class.java) }
}
package com.example.euvictodoist.di

import com.example.euvictodoist.network.ApiClient
import com.example.euvictodoist.network.repository.PostRepository
import com.example.euvictodoist.network.repository.PostRepositoryImpl
import com.example.euvictodoist.network.repository.TodoRepository
import org.koin.dsl.module

// Here you store your dependencies of your ViewModels
val repositoryModule = module {
    single { ApiClient() }
    single { TodoRepository(get()) }

    factory<PostRepository>{ PostRepositoryImpl(postService = get()) }
}

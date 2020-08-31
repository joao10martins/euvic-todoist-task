package com.example.euvictodoist.di

import com.example.euvictodoist.viewmodels.PostsViewModel
import com.example.euvictodoist.viewmodels.TodosViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

// Here you store your dependencies of your ViewModels
val viewModelModules = module {
    viewModel { TodosViewModel(get()) }
    viewModel { PostsViewModel(get()) }
}

package com.example.euvictodoist

import android.app.Application
import com.example.euvictodoist.di.repositoryModule
import com.example.euvictodoist.di.serviceModule
import com.example.euvictodoist.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestEuvicApplication : Application() {
    override fun onCreate(){
        super.onCreate()
        // start Koin!
        startKoin {
            // Android context
            androidContext(this@TestEuvicApplication)
            // modules
            modules(viewModelModules, repositoryModule, serviceModule)
        }
    }
}
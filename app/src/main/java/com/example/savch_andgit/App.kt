package com.example.savch_andgit

import android.app.Application
import com.example.savch_andgit.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}

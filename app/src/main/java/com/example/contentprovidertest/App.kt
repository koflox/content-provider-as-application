package com.example.contentprovidertest

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import com.example.koin_core.launchKoin
import com.example.koin_core.loadAndroidContext
import com.example.mylibrary1.Lib1Object
import com.example.mylibrary2.Lib2Object
import org.koin.android.ext.android.getKoin
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class AppObject

val appModule = module {
    single {
        AppObject()
    }
}

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        launchKoin {
            loadAndroidContext(this@App)
            loadKoinModules(appModule)
        }
        Log.d("Logos", "app, AppObject: ${getKoin().get<AppObject>()}")
        Log.d("Logos", "app, Lib2Object: ${getKoin().get<Lib2Object>()}")
        Log.d("Logos", "app, Lib1Object: ${getKoin().get<Lib1Object>()}")
    }

}
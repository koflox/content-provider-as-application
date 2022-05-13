package com.example.mylibrary2

import android.util.Log
import com.example.android_core.DefaultContentProvider
import com.example.koin_core.KoinSetup
import com.example.koin_core.launchKoin
import com.example.koin_core.loadAndroidContext
import org.koin.core.context.KoinContextHandler
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val modules = module {
    single { Lib2Object() }
}

class Lib2Object

class App : DefaultContentProvider() {

    override fun onCreate(): Boolean {
        val setupKoin: KoinSetup = {
            context?.let(::loadAndroidContext)
            loadKoinModules(modules)
        }
        launchKoin(setupKoin = setupKoin)
        Log.d("Logos", "lib2, Lib2Object: ${KoinContextHandler.getOrNull()?.get<Lib2Object>()}")
        return true
    }

}

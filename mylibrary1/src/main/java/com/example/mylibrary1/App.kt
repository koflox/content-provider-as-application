package com.example.mylibrary1

import android.util.Log
import com.example.android_core.DefaultContentProvider
import com.example.koin_core.KoinSetup
import com.example.koin_core.launchKoin
import com.example.koin_core.loadAndroidContext
import org.koin.core.context.KoinContextHandler
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class Lib1Object

val modules = module {
    single { Lib1Object() }
}

class ContentProviderTest : DefaultContentProvider() {

    override fun onCreate(): Boolean {
        val setupKoin: KoinSetup = {
            context?.let(::loadAndroidContext)
            loadKoinModules(modules)
        }
        launchKoin(setupKoin = setupKoin)
        Log.d("Logos", "lib1, Lib1Object: ${KoinContextHandler.getOrNull()?.get<Lib1Object>()}")
        return true
    }

}

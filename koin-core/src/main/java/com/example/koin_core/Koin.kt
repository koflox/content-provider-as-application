package com.example.koin_core

import android.app.Application
import android.content.Context
import org.koin.core.Koin
import org.koin.core.context.KoinContextHandler
import org.koin.core.context.startKoin
import org.koin.core.scope.ScopeDefinition
import org.koin.dsl.module

typealias KoinSetup = Koin.() -> Unit

fun launchKoin(setupKoin: KoinSetup) {
    when (val koin = KoinContextHandler.getOrNull()) {
        null -> startKoin {
            setupKoin.invoke(this.koin)
        }
        else -> setupKoin.invoke(koin)
    }
}

fun Koin.loadAndroidContext(androidContext: Context) {
    if (androidContext is Application) {
        if (getScopeOrNull(ScopeDefinition.ROOT_SCOPE_ID)?.getOrNull<Application>() == null) {
            loadModules(listOf(module {
                single { androidContext }
            }))
        }
    }
    if (getScopeOrNull(ScopeDefinition.ROOT_SCOPE_ID)?.getOrNull<Context>() == null) {
        loadModules(listOf(module {
            single { androidContext }
        }))
    }
}

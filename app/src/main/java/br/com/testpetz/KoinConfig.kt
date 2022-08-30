package br.com.testpetz

import android.app.Application
import br.com.network.di.injectNetworkKoinModule
import br.com.testpetz.card.di.injectCardKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun startKoinConfig(myApplication: Application) {
    startKoin { androidContext(myApplication) }
    injectModules()
}

fun injectModules() {
    injectNetworkKoinModule()
    injectCardKoinModule()
}
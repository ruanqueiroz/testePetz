package br.com.network.di

import br.com.network.executor.SafeRequest
import br.com.network.factory.ApiOkHttpClientFactory
import br.com.network.factory.ApiServiceFactory
import br.com.network.factory.OkHttpClientFactory
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectNetworkKoinModule() = loadKoinModule

private val loadKoinModule by lazy {
    loadKoinModules(
        module {
            factory<OkHttpClientFactory> {
                ApiOkHttpClientFactory()
            }
            factory { SafeRequest(Dispatchers.IO) }
            single { ApiServiceFactory(get()) }
        }
    )
}

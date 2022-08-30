package br.com.testpetz.card.di

import br.com.network.factory.ApiServiceFactory
import br.com.testpetz.card.data.api.CardApi
import br.com.testpetz.card.data.repository.CardRepositoryImpl
import br.com.testpetz.card.domain.repository.CardRepository
import br.com.testpetz.card.domain.usecase.CardInteractor
import br.com.testpetz.card.domain.usecase.CardInteractorImpl
import br.com.testpetz.card.presentation.detail.CardDetailViewModel
import br.com.testpetz.card.presentation.list.CardListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectCardKoinModule() {
    loadKoinModule
}

private val loadKoinModule by lazy {
    loadKoinModules(
        listOf(
            viewModelModule,
            interactorModule,
            repositoryModule,
            apiServiceModule
        )
    )
}

private val viewModelModule = module {
    viewModel { CardListViewModel(get()) }
    viewModel { CardDetailViewModel(get()) }
}

private val interactorModule = module {
    factory<CardInteractor> { CardInteractorImpl(get()) }
}

private val repositoryModule = module {
    single<CardRepository> { CardRepositoryImpl(get(), get()) }
}

private val apiServiceModule = module {
    factory { get<ApiServiceFactory>().create(CardApi::class.java) }
}
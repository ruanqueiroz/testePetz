package br.com.testpetz.card.data.repository

import br.com.network.entity.Result
import br.com.network.executor.SafeRequest
import br.com.testpetz.card.data.api.CardApi
import br.com.testpetz.card.data.entity.CardRemote
import br.com.testpetz.card.domain.repository.CardRepository

class CardRepositoryImpl(
    private val api: CardApi,
    private val safeRequest: SafeRequest
) : CardRepository {

    override suspend fun fetchCardList() = safeRequest {
        api.fetchCardList().map { it.transform() }
    }

    override suspend fun getCardByName(name: String) = safeRequest {
        api.getCardByName(name).map { it.transform() }
    }
}
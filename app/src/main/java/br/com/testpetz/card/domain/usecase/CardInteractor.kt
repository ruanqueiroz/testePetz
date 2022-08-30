package br.com.testpetz.card.domain.usecase

import br.com.network.entity.Result
import br.com.testpetz.card.domain.entity.Card

interface CardInteractor {

    suspend fun fetchCardList(): Result<List<Card>>
    suspend fun getCardByName(name: String): Result<List<Card>>
}
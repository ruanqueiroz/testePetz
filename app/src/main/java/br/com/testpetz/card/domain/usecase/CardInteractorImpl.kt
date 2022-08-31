package br.com.testpetz.card.domain.usecase

import br.com.testpetz.card.domain.repository.CardRepository

class CardInteractorImpl(
    private val repository: CardRepository
): CardInteractor {
    override suspend fun fetchCardList() = repository.fetchCardList()
    override suspend fun getCardByName(name: String) = repository.getCardByName(name)
}
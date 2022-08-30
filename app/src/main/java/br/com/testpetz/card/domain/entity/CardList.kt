package br.com.testpetz.card.domain.entity

data class CardList(
    var name: String? = null,
    var list: List<Card> = listOf()
)

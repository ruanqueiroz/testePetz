package br.com.testpetz.card.presentation.detail

sealed class CardDetailInteraction {
    data class Opened(val name: String) : CardDetailInteraction()
}

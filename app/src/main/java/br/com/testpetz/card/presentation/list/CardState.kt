package br.com.testpetz.card.presentation.list

import br.com.testpetz.card.components.scene.Async
import br.com.testpetz.card.domain.entity.Card

data class CardState(
    val tabs: Async<List<Card>>
)

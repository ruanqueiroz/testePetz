package br.com.testpetz.card.presentation.detail

import br.com.testpetz.card.components.scene.Async
import br.com.testpetz.card.domain.entity.Card

data class CardDetailState(
    val card: Async<Card>
)

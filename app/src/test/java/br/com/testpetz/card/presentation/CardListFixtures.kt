package br.com.testpetz.card.presentation

import br.com.testpetz.card.domain.entity.Card

object CardListFixtures {

    val cardDummy = Card(
        cardId = "1",
        name = "Nome do card",
        cardSet = "Classic",
        type = "Normal",
        faction = "A",
        rarity = "AA",
        cost = "1",
        text = "Texto do card",
        playerClass = "a",
        attack = "5",
        health = "6",
        elite = false,
        artist = "AAAA",
        race = "AA",
        img = "a",
        imgGold = "a",
        descriptionFlavor = "Descricao mais longa do card"
    )
}
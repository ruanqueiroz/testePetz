package br.com.testpetz.card.domain.entity

data class Card(
    var cardId: String? = null,
    var name: String? = null,
    var cardSet: String? = null,
    var type: String? = null,
    var faction: String? = null,
    var rarity: String? = null,
    var cost: String? = null,
    var text: String? = null,
    var playerClass: String? = null,
    var attack: String? = null,
    var health: String? = null,
    var elite: Boolean? = null,
    var artist: String? = null,
    var race: String? = null,
    var img: String? = null,
    var imgGold: String? = null,
    var descriptionFlavor: String? = null
)

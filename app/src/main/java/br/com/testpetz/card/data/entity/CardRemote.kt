package br.com.testpetz.card.data.entity

import br.com.testpetz.card.domain.entity.Card
import com.squareup.moshi.Json

data class CardRemote(
    @field:Json(name = "cardId")
    private var cardId: String? = null,
    @field:Json(name = "name")
    private var name: String? = null,
    @field:Json(name = "cardSet")
    private var cardSet: String? = null,
    @field:Json(name = "type")
    private var type: String? = null,
    @field:Json(name = "faction")
    private var faction: String? = null,
    @field:Json(name = "rarity")
    private var rarity: String? = null,
    @field:Json(name = "cost")
    private var cost: String? = null,
    @field:Json(name = "text")
    private var text: String? = null,
    @field:Json(name = "playerClass")
    private var playerClass: String? = null,
    @field:Json(name = "attack")
    private var attack: String? = null,
    @field:Json(name = "health")
    private var health: String? = null,
    @field:Json(name = "elite")
    private var elite: Boolean? = null,
    @field:Json(name = "artist")
    private var artist: String? = null,
    @field:Json(name = "race")
    private var race: String? = null,
    @field:Json(name = "img")
    private var img: String? = null,
    @field:Json(name = "imgGold")
    private var imgGold: String? = null,
    @field:Json(name = "flavor")
    private var descriptionFlavor: String? = null
) {
    fun transform() = Card(
        cardId = this.cardId,
        name = this.name,
        cardSet = this.cardSet,
        type = this.type,
        faction = this.faction,
        rarity = this.rarity,
        cost = this.cost,
        text = this.text,
        playerClass = this.playerClass,
        attack = this.attack,
        health = this.health,
        elite = this.elite,
        artist = this.artist,
        race = this.race,
        img = this.img,
        imgGold = this.imgGold,
        descriptionFlavor = this.descriptionFlavor
    )
}


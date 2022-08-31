package br.com.testpetz.card.data.api

import br.com.testpetz.card.data.entity.CardRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface CardApi {

    @GET("cards/sets/Classic")
    suspend fun fetchCardList(): List<CardRemote>

    @GET("cards/{name}")
    suspend fun getCardByName(@Path("name") name: String): List<CardRemote>
}
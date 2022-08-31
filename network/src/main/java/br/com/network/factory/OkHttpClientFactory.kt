package br.com.network.factory

import okhttp3.OkHttpClient

interface OkHttpClientFactory {
    fun create(): OkHttpClient
}

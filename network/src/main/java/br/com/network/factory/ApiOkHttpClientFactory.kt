package br.com.network.factory

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class ApiOkHttpClientFactory() : OkHttpClientFactory {

    override fun create(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(getLoggingInterceptor())
            .addInterceptor { chain ->
                chain.proceed(chain.request().addUserContentToHeader())
            }
            .build()
    }

    private fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun Request.addUserContentToHeader(): Request {
        return newBuilder()
            .header("Content-Type", "application/json")
            .header("X-RapidAPI-Key", "73ce7e29f2msheb2ead29f16b4fcp1c4dd9jsn73d34acc8ebc")
            .header("X-RapidAPI-Host", "omgvamp-hearthstone-v1.p.rapidapi.com")
            .method(this.method, this.body)
            .build()
    }
}

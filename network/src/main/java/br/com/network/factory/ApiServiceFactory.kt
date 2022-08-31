package br.com.network.factory

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServiceFactory(
    okHttpClientFactory: OkHttpClientFactory
) : ServiceFactory {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://omgvamp-hearthstone-v1.p.rapidapi.com/")
        .client(okHttpClientFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    override fun <T> create(service: Class<T>): T = retrofit.create(service)
}

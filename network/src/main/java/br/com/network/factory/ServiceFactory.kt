package br.com.network.factory

interface ServiceFactory {
    fun <T> create(service: Class<T>): T
}

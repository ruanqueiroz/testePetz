package br.com.testpetz

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoinConfig(this)
    }
}
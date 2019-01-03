package com.example.frederikdeprez.tennistime

import android.app.Application
import com.example.frederikdeprez.tennistime.di.AppComponent
import com.example.frederikdeprez.tennistime.util.Constants.Companion.BASE_URL

/**
 * Main start up of the application and used to instantiate Dagger injector
 */
class Application: Application() {
    companion object {
        //platformStatic allow access it from java code
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent.create(this, BASE_URL)
    }
}
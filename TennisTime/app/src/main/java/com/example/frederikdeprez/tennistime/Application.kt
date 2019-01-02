package com.example.frederikdeprez.tennistime

import com.example.frederikdeprez.tennistime.di.AppComponent

/**
 * Main start up of the application and used to instantiate Dagger injector
 */
class Application {
    companion object {
        //platformStatic allow access it from java code
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

//    override fun onCreate() {
//        super.onCreate()
//        appComponent = AppComponent.create(this, BASE_URL)
//    }
}
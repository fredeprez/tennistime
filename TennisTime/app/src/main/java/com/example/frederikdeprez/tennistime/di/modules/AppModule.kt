package com.example.frederikdeprez.tennistime.di.modules

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.frederikdeprez.tennistime.util.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    /**
     * Provide a singleton of [Context]
     */
    @Provides
    @Singleton
    fun provideContext(): Context {
        return application.applicationContext
    }

    /**
     * Proved a singleton of [SharedPreferences]
     */
    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.PREFS_KEY, Activity.MODE_PRIVATE)
    }
}
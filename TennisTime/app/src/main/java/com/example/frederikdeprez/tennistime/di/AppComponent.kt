package com.example.frederikdeprez.tennistime.di

import android.app.Application
import com.example.frederikdeprez.tennistime.di.modules.AppModule
import com.example.frederikdeprez.tennistime.ui.tabs.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Register all the classes that require injectables with Dagger
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)


    /**
     * Create singleton of [AppComponent] with [AppModule], [DataModule] and [NetModule] defined
     */
    companion object Factory {
        fun create(app: Application): AppComponent {
            return DaggerAppComponent
                    .builder()
                    .appModule(AppModule(app))
                    .build()
        }
    }
}
package com.example.frederikdeprez.tennistime.di

import android.app.Application
import com.example.frederikdeprez.tennistime.data.repositories.InjectableRepository
import com.example.frederikdeprez.tennistime.data.repositories.TennisclubRepository
import com.example.frederikdeprez.tennistime.di.modules.AppModule
import com.example.frederikdeprez.tennistime.di.modules.DataModule
import com.example.frederikdeprez.tennistime.di.modules.NetModule
import com.example.frederikdeprez.tennistime.ui.tabs.MainActivity
import com.example.frederikdeprez.tennistime.ui.viewmodels.PlayerViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Register all the classes that require injectables with Dagger
 */
@Singleton
@Component(modules = [AppModule::class, NetModule::class, DataModule::class])
interface AppComponent {

    /**
     * Register all classes that have injectables
     */
    fun inject(mainActivity: MainActivity)

    fun inject(repository: InjectableRepository)
    fun inject(playerViewModel: PlayerViewModel)


    /**
     * Create singleton of [AppComponent] with [AppModule], [DataModule] and [NetModule] defined
     */
    companion object Factory {
        fun create(app: Application, baseUrl: String): AppComponent {
            return DaggerAppComponent
                    .builder()
                    .appModule(AppModule(app))
                    .netModule(NetModule(baseUrl))
                    .dataModule(DataModule())
                    .build()
        }
    }
}
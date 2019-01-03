package com.example.frederikdeprez.tennistime.di.modules

import com.example.frederikdeprez.tennistime.data.network.API
import com.example.frederikdeprez.tennistime.data.repositories.PlayerRepository
import com.example.frederikdeprez.tennistime.data.repositories.TennisclubRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Define all injectables related to network and data
 */
@Module
class DataModule {

    /**
     * Provide a singleton of [API]
     */
    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): API {
        return retrofit.create(API::class.java)
    }

    /**
     * Provide a singleton of [TennisclubRepository]
     */
    @Singleton
    @Provides
    fun provideTennisclubRepository(): TennisclubRepository {
        return TennisclubRepository()
    }

    /**
     * Provide a singleton of [PlayerRepository]
     */
    @Singleton
    @Provides
    fun providePlayerRepository(): PlayerRepository {
        return PlayerRepository()
    }
}
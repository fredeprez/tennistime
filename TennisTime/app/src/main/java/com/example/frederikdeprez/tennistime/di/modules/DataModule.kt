package com.example.frederikdeprez.tennistime.di.modules

import com.example.frederikdeprez.tennistime.data.network.API
import com.example.frederikdeprez.tennistime.data.repositories.PlayerRepository
import com.example.frederikdeprez.tennistime.data.repositories.TennisclubRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

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

    @Singleton
    @Provides
    fun provideTennisclubRepository(): TennisclubRepository {
        return TennisclubRepository()
    }

    @Singleton
    @Provides
    fun providePlayerRepository(): PlayerRepository {
        return PlayerRepository()
    }
}
package com.example.frederikdeprez.tennistime.di.modules

import android.content.Context
import androidx.room.Room
import com.example.frederikdeprez.tennistime.data.db.AppDatabase
import com.example.frederikdeprez.tennistime.data.db.PlayerDataDao
import com.example.frederikdeprez.tennistime.data.db.TennisclubDataDao
import com.example.frederikdeprez.tennistime.data.network.API
import com.example.frederikdeprez.tennistime.data.repositories.PlayerRepository
import com.example.frederikdeprez.tennistime.data.repositories.TennisclubRepository
import com.example.frederikdeprez.tennistime.util.Constants.Companion.DATABASE_NAME
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
    fun provideTennisclubRepository(tennisclubDataDao: TennisclubDataDao): TennisclubRepository {
        return TennisclubRepository(tennisclubDataDao)
    }

    /**
     * Provide a singleton of [PlayerRepository]
     */
    @Singleton
    @Provides
    fun providePlayerRepository(playerDataDao: PlayerDataDao): PlayerRepository {
        return PlayerRepository(playerDataDao)
    }

    /**
     * Provide a singleton of [AppDatabase]
     *
     * @param context instance of [Context] provided by dagger
     * @see [AppModule]
     *
     * @return singleton of [AppDatabase]
     */
    @Provides
    @Singleton
    fun appDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME).allowMainThreadQueries().build()
    }

    /**
     * Provide a singleton of [PlayerDataDao]
     *
     * @param appDatabase instance of [AppDatabase] provided by dagger
     * @see [DataModule]
     *
     * @return singleton of [PlayerDataDao]
     */
    @Provides
    @Singleton
    fun playerDataDao(appDatabase: AppDatabase): PlayerDataDao {
        return appDatabase.playerDataDao()
    }

    /**
     * Provide a singleton of [TennisclubDataDao]
     *
     * @param appDatabase instance of [AppDatabase] provided by dagger
     * @see [DataModule]
     *
     * @return singleton of [TennisclubDataDao]
     */
    @Provides
    @Singleton
    fun tennisclubDataDao(appDatabase: AppDatabase): TennisclubDataDao {
        return appDatabase.tennisclubDataDao()
    }
}
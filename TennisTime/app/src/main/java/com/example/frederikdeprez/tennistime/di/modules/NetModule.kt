package com.example.frederikdeprez.tennistime.di.modules

import android.app.Application
import com.example.frederikdeprez.tennistime.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Define all injectables related to network traffic
 */
@Module(includes = [DataModule::class])
class NetModule(private val baseUrl: String) {

    /**
     * Provide a singleton of [HttpLoggingInterceptor] to log the body of every api call
     */
    @Provides
    @Singleton
    fun provideLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /**
     * Provide a singleton of [Cache]
     *
     * @param application instance of [Application] provided by dagger
     * @see [AppModule]
     *
     * @return singleton of [Cache]
     */
    @Provides
    @Singleton
    fun provideHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024L
        return Cache(application.cacheDir, cacheSize)
    }

    /**
     * Provide a singleton of [GsonConverterFactory]
     */
    @Provides
    @Singleton
    fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    /**
     * Provide a singleton of [OkHttpClient] and requires [logger] and [headerInterceptor] defined in [NetModule]
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache, logger: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(logger)
                .build()
    }

    /**
     * Provide a singleton of [RxJava2CallAdapterFactory]
     */
    @Provides
    @Singleton
    fun provideRxJava(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    /**
     * Provide a singleton of [Retrofit] and requires [gson], [okHttpClient] and [rxJava] defined in [NetModule]
     */
    @Provides
    @Singleton
    fun provideRetrofit(gson: GsonConverterFactory, okHttpClient: OkHttpClient, rxJava: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gson)
                .addCallAdapterFactory(rxJava)
                .client(okHttpClient)
                .build()
    }
}
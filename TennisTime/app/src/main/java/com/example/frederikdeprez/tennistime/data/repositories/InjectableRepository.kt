package com.example.frederikdeprez.tennistime.data.repositories

import android.content.Context
import android.net.ConnectivityManager
import com.example.frederikdeprez.tennistime.Application
import com.example.frederikdeprez.tennistime.data.network.API
import javax.inject.Inject

/**
 * Superclass repository that registers its subclasses with dagger so the can use injectables
 *
 * [TennisclubRepository] [PlayerRepository] extend this repository
 */
abstract class InjectableRepository {

    @Inject
    lateinit var api: API

    init {
        // register with dagger
        Application.appComponent.inject(this)
    }

}
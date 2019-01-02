package com.example.frederikdeprez.tennistime.data.repositories

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

/**
 * Superclass repository that registers its subclasses with dagger so the can use injectables
 *
 * [CategoryRepository] [ExpenseRepository] and [StatisticRepository] extend this repository
 */
abstract class InjectableRepository {

    @Inject
    lateinit var context: Context

    /**
     * Check whether the phone has internet connectivity
     */
    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected
    }

    /**
     * Decide which method should be executed when the user is offline
     *
     * [onlineFunction] is the function that should be executed when the phone has internet connection
     * [offlineFunction] is the function that should be executed when the phone has no internet connection
     */
    fun <T> doAction(onlineFunction: () -> T, offlineFunction: () -> T): T {
        return if (isConnected()) {
            onlineFunction.invoke()
        } else {
            offlineFunction.invoke()
        }
    }

//    init {
////        // register with dagger
////        App.appComponent.inject(this)
////    }

}
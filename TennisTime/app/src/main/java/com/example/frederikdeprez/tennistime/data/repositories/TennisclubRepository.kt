package com.example.frederikdeprez.tennistime.data.repositories

import android.util.Log
import com.example.frederikdeprez.tennistime.data.Tennisclub
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TennisclubRepository: InjectableRepository() {

    /**
     * Get all the [Tennisclub] from [API]
     */
    fun getAllTennisClubs(): Single<List<Tennisclub>> {
        return api.getAllTennisclubs().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSuccess{
            it.forEach {
                Log.i("FREDSON", "repo " + it.toString())
            }
        }.doOnError {
            Log.i("FRED_EX", it.toString())
        }
    }
}
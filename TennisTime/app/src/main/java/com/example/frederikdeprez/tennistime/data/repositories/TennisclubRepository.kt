package com.example.frederikdeprez.tennistime.data.repositories

import android.util.Log
import com.example.frederikdeprez.tennistime.data.Tennisclub
import com.example.frederikdeprez.tennistime.data.db.TennisclubDataDao
import com.example.frederikdeprez.tennistime.data.network.response.TennisclubDTO
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class TennisclubRepository @Inject constructor(private val tennisclubDataDao: TennisclubDataDao): InjectableRepository() {

    private lateinit var items: ArrayList<TennisclubDTO>

    fun getAllTennisClubs(): Single<List<Tennisclub>> {

        // Check if database table tennisclubs is empty
        return if (tennisclubDataDao.getRowCount() <= 0) {
            api.getAllTennisclubs().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).map {
                //convert TennisclubDTO to Tennisclub
                it.map { item -> TennisclubDTO.toTennisclub(item) }
            }.doOnSuccess {
                doAsync {
                    tennisclubDataDao.insertMany(it)
                }
            }
        } else {
            // Get all data from database
            tennisclubDataDao.getAllTennisclubs().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
        }
    }
}
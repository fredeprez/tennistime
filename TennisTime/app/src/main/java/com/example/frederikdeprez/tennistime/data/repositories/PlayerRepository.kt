package com.example.frederikdeprez.tennistime.data.repositories

import android.util.Log
import com.example.frederikdeprez.tennistime.data.Player
import com.example.frederikdeprez.tennistime.data.Tennisclub
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PlayerRepository: InjectableRepository() {

    /**
     * Get all the [Player] from [API] from specific [Tennisclub]
     */
    fun getAllPlayersFromTennisclub(tennisclub_id: Long): Single<List<Player>>? {
        return api.getAllPlayersFromTennisclub(tennisclub_id).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSuccess{
            it.forEach {
                Log.i("FRED", it.toString())
            }
        }.doOnError {
            Log.i("FRED_EX", it.toString())
        }
    }

    /**
     * Get [Player] from [API] from specific [Tennisclub]
     */
    fun getPlayerFromTennisclub(tennisclub_id: Long, player_id: Long): Single<Player>? {
        return api.getPlayerFromTennisclub(tennisclub_id, player_id).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSuccess{
            Log.i("FRED", it.toString())
        }.doOnError {
            Log.i("FRED_EX", it.toString())
        }
    }

    fun registerNewPlayerInTennisclub(tennisclub_id: Long, player: Player): Single<Player>? {
        return api.registerNewPlayerInTennisclub(tennisclub_id, player).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSuccess {
            Log.i("FRED", it.toString())
        }.doOnError {
            Log.i("FRED_EX", it.toString())
        }
    }

    fun changePlayer(tennisclub_id: Long, player_id: Long, player: Player): Single<Player>? {
        return api.changePlayer(tennisclub_id, player_id, player).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSuccess{
            Log.i("FRED", it.toString())
        }.doOnError {
            Log.i("FRED_EX", it.toString())
        }
    }


}
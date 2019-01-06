package com.example.frederikdeprez.tennistime.data.repositories

import android.util.Log
import com.example.frederikdeprez.tennistime.data.Player
import com.example.frederikdeprez.tennistime.data.Tennisclub
import com.example.frederikdeprez.tennistime.data.db.PlayerDataDao
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlayerRepository @Inject constructor(private val playerDataDao: PlayerDataDao): InjectableRepository() {

    /**
     * Get all the [Player] from [API] from specific [Tennisclub]
     */
    fun getAllPlayersFromTennisclub(tennisclubId: String): Single<List<Player>> {
        return api.getAllPlayersFromTennisclub(tennisclubId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSuccess{
            it.forEach {
                Log.i("FREDSON", it.toString())
            }
        }.doOnError {
            Log.i("FRED_EX", it.toString())
        }
    }

    /**
     * Get [Player] from [API] from specific [Tennisclub]
     */
    fun getPlayerFromTennisclub(tennisclubId: String, playerId: String): Single<Player> {
        return api.getPlayerFromTennisclub(tennisclubId, playerId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSuccess{
            Log.i("FREDSON", it.toString())
        }.doOnError {
            Log.i("FRED_EX", it.toString())
        }
    }

    fun registerNewPlayerInTennisclub(tennisclubId: String, player: Player): Single<Player> {
        return api.registerNewPlayerInTennisclub(tennisclubId, player).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSuccess {
            Log.i("FREDSON", it.toString())
        }.doOnError {
            Log.i("FRED_EX", it.toString())
        }
    }

    fun changePlayer(tennisclubId: String, playerId: String, player: Player): Single<Player> {
        return api.changePlayer(tennisclubId, playerId, player).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSuccess{
            Log.i("FREDSON", it.toString())
        }.doOnError {
            Log.i("FRED_EX", it.toString())
        }
    }


}
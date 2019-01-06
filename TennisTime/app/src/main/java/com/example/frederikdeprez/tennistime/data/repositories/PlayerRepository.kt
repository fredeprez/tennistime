package com.example.frederikdeprez.tennistime.data.repositories

import android.util.Log
import com.example.frederikdeprez.tennistime.data.Player
import com.example.frederikdeprez.tennistime.data.Tennisclub
import com.example.frederikdeprez.tennistime.data.db.PlayerDataDao
import com.example.frederikdeprez.tennistime.data.network.response.PlayerDTO
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class PlayerRepository @Inject constructor(private val playerDataDao: PlayerDataDao): InjectableRepository() {

    private lateinit var items: ArrayList<PlayerDataDao>

    fun getAllPlayers(): Single<List<Player>> {

        // Check if database table players is empty
        return if (playerDataDao.getRowCount() <= 0) {
            api.getAllPlayers().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).map {
                // Convert PlayerDTO to Player
                it.map { item -> PlayerDTO.toPlayer(item) }
            }.doOnSuccess {
                doAsync {
                    playerDataDao.insertMany(it)
                }
            }
        } else {
            // Get all players belonging to tennisclub from database
            playerDataDao.getAllPlayers().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
        }
    }

    /**
     * Get all the [Player] from [API] from specific [Tennisclub]
     */
    fun getAllPlayersFromTennisclub(tennisclubId: String): Single<List<Player>> {

        // Check if database table players is empty
        return if (playerDataDao.getRowCount() <= 0) {
            api.getAllPlayersFromTennisclub(tennisclubId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).map {
                // Convert PlayerDTO to Player
                it.map { item -> PlayerDTO.toPlayer(item) }
            }
        } else {
            // Get all players belonging to tennisclub from database
            playerDataDao.getAllPlayersFromTennisclub(tennisclubId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
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
package com.example.frederikdeprez.tennistime.ui.viewmodels

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.frederikdeprez.tennistime.Application
import com.example.frederikdeprez.tennistime.data.Player
import com.example.frederikdeprez.tennistime.ui.player.PlayerFragmentActions
import com.example.frederikdeprez.tennistime.util.Constants.Companion.PREFS_KEY
import io.reactivex.rxkotlin.addTo

class PlayerViewModel: BaseViewModel(), PlayerFragmentActions {

    private var playerName = MutableLiveData<String>()

    fun bind(player: Player) {
        playerName.value = player.name
    }

    fun getPlayerName(): MutableLiveData<String> {
        return playerName
    }

    private var _mutablePlayer = MutableLiveData<Player>()
    var mutablePlayer: LiveData<Player> = _mutablePlayer

    init {
        getAllPlayersToFillDatabase()
        if(sharedPreferences.getString("playerId", "0") != "0" && sharedPreferences.getString("tennisclubId", "0") != "0") {
            val playerId: String = sharedPreferences.getString("playerId", "0")
            val tennisclubId: String = sharedPreferences.getString("tennisclubId", "0")
            getPlayerFromTennisclub(tennisclubId, playerId)
        }
    }

    private fun getAllPlayersToFillDatabase() {
        playerRepository.getAllPlayers().subscribe().addTo(compositeDisposable)
    }

    fun getPlayerFromTennisclub(tennisclubId: String, playerId: String) {
        playerRepository.getPlayerFromTennisclub(tennisclubId, playerId)
                .subscribe({
                    _mutablePlayer.value = it
                    sharedPreferences.edit().putString("name", it.name).apply()
                }, {
                    Log.i("FREDEX", it.toString())
                })
                .addTo(compositeDisposable)
    }

    override fun registerNewPlayerInTennisclub(tennisclubId: String, player: Player) {
        if(isValid(player) && sharedPreferences.getString("playerId", "0") == "0") {
            playerRepository.registerNewPlayerInTennisclub(tennisclubId, player)
                    .subscribe({
                        _mutablePlayer.value = it
                        sharedPreferences.edit().putString("playerId", it.playerId).apply()
                        sharedPreferences.edit().putString("tennisclubId", it.tennisclubId).apply()
                    }, {
                        Log.i("FREDEX", it.toString())
                    })
                    .addTo(compositeDisposable)
        }
    }

    override fun changePlayer(tennisclubId: String, playerId: String, player: Player) {
        if(isValid(player)) {
            playerRepository.changePlayer(tennisclubId, playerId, player)
                    .subscribe({
                        _mutablePlayer.value = it
                    }, {
                        Log.i("FREDEX", it.toString())
                    })
                    .addTo(compositeDisposable)
        }
    }

    private fun isValid(player: Player): Boolean {
        if (player.name.isEmpty() || player.tennisclubId.isEmpty() || player.email.isEmpty() || player.phonenumber.isEmpty() || player.ranking.isEmpty()) {
            return false
        }
        return true
    }
}
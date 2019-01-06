package com.example.frederikdeprez.tennistime.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.frederikdeprez.tennistime.Application
import com.example.frederikdeprez.tennistime.data.Player
import com.example.frederikdeprez.tennistime.ui.player.PlayerFragmentActions
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
        if(mutablePlayer.value != null) {
            getPlayerFromTennisclub(mutablePlayer.value!!.tennisclubId, mutablePlayer.value!!.playerId)
        }
    }

    fun getPlayerFromTennisclub(tennisclubId: String, playerId: String) {
        playerRepository.getPlayerFromTennisclub(tennisclubId, playerId)
                .subscribe({
                    _mutablePlayer.value = it
                }, {
                    Log.i("FREDEX", it.toString())
                })
                .addTo(compositeDisposable)
    }

    override fun registerNewPlayerInTennisclub(tennisclubId: String, player: Player) {
        if(isValid(player)) {
            playerRepository.registerNewPlayerInTennisclub(tennisclubId, player)
                    .subscribe({
                        _mutablePlayer.value = it
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
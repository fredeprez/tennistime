package com.example.frederikdeprez.tennistime.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.frederikdeprez.tennistime.data.Player
import com.example.frederikdeprez.tennistime.ui.match.MatchListAdapterActions
import com.example.frederikdeprez.tennistime.util.Event
import io.reactivex.rxkotlin.addTo

class MatchViewModel(private val tennisclubId: String): BaseViewModel(), MatchListAdapterActions {

    private val _playerList = MutableLiveData<List<Player>>()
    val playerList: LiveData<List<Player>> = _playerList

    private val _selectedPlayer = MutableLiveData<Event<Player>>()
    val selectedPlayer: LiveData<Event<Player>> = _selectedPlayer

    init {
        getAllPlayers()
        getAllPlayersFromTennisclub(tennisclubId)
    }

    fun getAllPlayers() {
        playerRepository.getAllPlayers()
                .subscribe({
                    _playerList.value = it
                }, {
                    Log.i("FREDEX", it.toString())
                })
                .addTo(compositeDisposable)
    }

    fun getAllPlayersFromTennisclub(tennisclubId: String) {
        playerRepository.getAllPlayersFromTennisclub(tennisclubId)
                .subscribe({
                    _playerList.value = it
                }, {
                    Log.i("FREDEX", it.toString())
                })
                .addTo(compositeDisposable)
    }

    override fun pressButton(player: Player) {
        _selectedPlayer.value = Event(player.copy())
    }
}

class MyViewModelFactory(
        private val tennisclubId: String
): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        return MatchViewModel(tennisclubId) as T
    }
}
package com.example.frederikdeprez.tennistime.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.frederikdeprez.tennistime.data.Player
import com.example.frederikdeprez.tennistime.ui.match.MatchListAdapterActions
import com.example.frederikdeprez.tennistime.ui.match.MatchSearchActions
import com.example.frederikdeprez.tennistime.util.Event
import io.reactivex.rxkotlin.addTo

class MatchViewModel(): BaseViewModel(), MatchListAdapterActions, MatchSearchActions {

    private lateinit var _repoPlayers: List<Player>
    private val _playerList = MutableLiveData<List<Player>>()
    val playerList: LiveData<List<Player>> = _playerList

    private val _selectedPlayer = MutableLiveData<Event<Player>>()
    val selectedPlayer: LiveData<Event<Player>> = _selectedPlayer

    init {
        getAllPlayers()
    }

    fun getAllPlayers() {
        playerRepository.getAllPlayers()
                .subscribe({
                    _repoPlayers = it
                    _playerList.value = it
                }, {
                })
                .addTo(compositeDisposable)
    }

    fun getAllPlayersFromTennisclub(tennisclubId: String) {
        playerRepository.getAllPlayersFromTennisclub(tennisclubId)
                .subscribe({
                    _playerList.value = it
                }, {
                })
                .addTo(compositeDisposable)
    }

    override fun filter(query: String?) {
        with(query ?: "") {
            if (!this.isEmpty()) {
                val pattern = this.toLowerCase().trim()
                _playerList.value = _repoPlayers.filter { player ->
                    player.name.toLowerCase().contains(pattern)
                }
            } else
                _playerList.value = _repoPlayers
        }
    }

    override fun pressButton(player: Player) {
        _selectedPlayer.value = Event(player.copy())
    }
}

//class MyViewModelFactory(
//        private val tennisclubId: String
//): ViewModelProvider.NewInstanceFactory() {
//    override fun <T: ViewModel> create(modelClass:Class<T>): T {
//        return MatchViewModel(tennisclubId) as T
//    }
//}
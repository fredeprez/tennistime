package com.example.frederikdeprez.tennistime.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.frederikdeprez.tennistime.data.Player
import io.reactivex.rxkotlin.addTo

class MatchViewModel: BaseViewModel() {

    private val _playerList = MutableLiveData<List<Player>>()
    val playerList: LiveData<List<Player>> = _playerList

    init {
        getAllPlayersFromTennisclub()
    }

    fun getAllPlayersFromTennisclub() {
        playerRepository.getAllPlayersFromTennisclub(1)
                .subscribe({
                    _playerList.value = it
                }, {
                    Log.i("FREDEX", it.toString())
                })
                .addTo(compositeDisposable)
    }


}
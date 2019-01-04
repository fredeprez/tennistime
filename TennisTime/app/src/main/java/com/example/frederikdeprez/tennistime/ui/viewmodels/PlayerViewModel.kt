package com.example.frederikdeprez.tennistime.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.frederikdeprez.tennistime.Application
import com.example.frederikdeprez.tennistime.data.Player

class PlayerViewModel: ViewModel() {

    private var playerName = MutableLiveData<String>()

//    init {
//        Application.appComponent.inject(this)
//    }

    fun bind(player: Player) {
        playerName.value = player.name
    }

    fun getPlayerName(): MutableLiveData<String> {
        return playerName
    }
}
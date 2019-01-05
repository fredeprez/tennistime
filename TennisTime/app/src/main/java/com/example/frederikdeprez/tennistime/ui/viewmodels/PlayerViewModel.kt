package com.example.frederikdeprez.tennistime.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.frederikdeprez.tennistime.Application
import com.example.frederikdeprez.tennistime.data.Player

class PlayerViewModel: BaseViewModel() {

    private var playerName = MutableLiveData<String>()

    fun bind(player: Player) {
        playerName.value = player.name
    }

    fun getPlayerName(): MutableLiveData<String> {
        return playerName
    }
}
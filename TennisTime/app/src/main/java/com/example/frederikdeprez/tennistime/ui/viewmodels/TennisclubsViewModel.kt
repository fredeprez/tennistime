package com.example.frederikdeprez.tennistime.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.frederikdeprez.tennistime.data.Tennisclub
import com.example.frederikdeprez.tennistime.data.repositories.TennisclubRepository
import com.example.frederikdeprez.tennistime.ui.tennisclubs.TennisclubListAdapterActions
import com.example.frederikdeprez.tennistime.ui.tennisclubs.TennisclubsFragment
import com.example.frederikdeprez.tennistime.util.Event
import io.reactivex.rxkotlin.addTo

class TennisclubsViewModel: BaseViewModel(), TennisclubListAdapterActions {

    private val _tennisclubList = MutableLiveData<List<Tennisclub>>()
    val tennisclubList: LiveData<List<Tennisclub>> = _tennisclubList

    private val _selectedTennisclub = MutableLiveData<Event<Tennisclub>>()
    val selectedTennisclub: LiveData<Event<Tennisclub>> = _selectedTennisclub

    init {
        Log.i("FREDSON", "kom ik hier")
        getAllTennisclubs()
    }

    private fun getAllTennisclubs() {
        tennisclubRepository.getAllTennisClubs()
                .subscribe({
                    _tennisclubList.value = it
                }, {
                    Log.i("FREDEX", it.toString())
                })
                .addTo(compositeDisposable)
    }

    override fun select(tennisclub: Tennisclub) {
        Log.i("FREDSON", "select")
        _selectedTennisclub.value = Event(tennisclub.copy())
        Log.i("FREDSON", "selected: " + _selectedTennisclub.value.toString())
    }

}
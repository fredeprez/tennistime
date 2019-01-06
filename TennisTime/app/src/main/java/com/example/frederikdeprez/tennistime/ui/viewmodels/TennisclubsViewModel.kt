package com.example.frederikdeprez.tennistime.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.frederikdeprez.tennistime.data.Tennisclub
import com.example.frederikdeprez.tennistime.data.repositories.TennisclubRepository
import com.example.frederikdeprez.tennistime.ui.tennisclubs.TennisclubListAdapterActions
import com.example.frederikdeprez.tennistime.ui.tennisclubs.TennisclubsFragment
import com.example.frederikdeprez.tennistime.ui.tennisclubs.TennisclubsSearchActions
import com.example.frederikdeprez.tennistime.util.Event
import io.reactivex.rxkotlin.addTo

class TennisclubsViewModel: BaseViewModel(), TennisclubListAdapterActions, TennisclubsSearchActions {

    private lateinit var _repoTennisclubs: List<Tennisclub>
    private val _tennisclubList = MutableLiveData<List<Tennisclub>>()
    val tennisclubList: LiveData<List<Tennisclub>> = _tennisclubList

    private val _selectedTennisclub = MutableLiveData<Event<Tennisclub>>()
    val selectedTennisclub: LiveData<Event<Tennisclub>> = _selectedTennisclub

    init {
        getAllTennisclubs()
    }

    private fun getAllTennisclubs() {
        tennisclubRepository.getAllTennisClubs()
                .subscribe({
                    _repoTennisclubs = it
                    _tennisclubList.value = it
                    sharedPreferences.edit().putInt("tennisclubs_size", it.size)
                }, {
                })
                .addTo(compositeDisposable)
    }

    override fun filter(query: String?) {
        with(query ?: "") {
            if (!this.isEmpty()) {
                val pattern = this.toLowerCase().trim()
                _tennisclubList.value = _repoTennisclubs.filter { tennisclub ->
                    tennisclub.name.toLowerCase().contains(pattern)
                }
            } else
                _tennisclubList.value = _repoTennisclubs
        }
    }

    override fun select(tennisclub: Tennisclub) {
        _selectedTennisclub.value = Event(tennisclub.copy())
    }

}
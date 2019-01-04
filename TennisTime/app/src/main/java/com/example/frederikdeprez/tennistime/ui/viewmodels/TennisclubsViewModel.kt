package com.example.frederikdeprez.tennistime.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.frederikdeprez.tennistime.data.Tennisclub
import com.example.frederikdeprez.tennistime.data.repositories.TennisclubRepository
import com.example.frederikdeprez.tennistime.ui.tennisclubs.TennisclubsFragment
import io.reactivex.rxkotlin.addTo

class TennisclubsViewModel: BaseViewModel() {

    val tennisclubsRecyclerViewAdapter = TennisclubsFragment.TennisclubsRecyclerViewAdapter()

//    private val _tennisclubList = MutableLiveData<List<Tennisclub>>()
//    val tennisclubList: LiveData<List<Tennisclub>> = _tennisclubList

    init {
        Log.i("FREDSON", "kom ik hier")
        getAllTennisclubs()
    }

    private fun getAllTennisclubs() {
        tennisclubRepository.getAllTennisClubs()
                .subscribe({
                    onRetrieveTennisclubsListSuccess(it)
//                    _tennisclubList.value = it
                }, {
                    Log.i("FREDEX", it.toString())
                })
                .addTo(compositeDisposable)
    }

    private fun onRetrieveTennisclubsListSuccess(tennisclubList:List<Tennisclub>){
        tennisclubsRecyclerViewAdapter.updateTennisclubs(tennisclubList)
    }
}
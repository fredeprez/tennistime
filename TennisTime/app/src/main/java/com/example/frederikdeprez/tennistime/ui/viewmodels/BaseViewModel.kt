package com.example.frederikdeprez.tennistime.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.frederikdeprez.tennistime.Application
import com.example.frederikdeprez.tennistime.data.repositories.PlayerRepository
import com.example.frederikdeprez.tennistime.data.repositories.TennisclubRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

abstract class BaseViewModel: ViewModel() {

    val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var tennisclubRepository: TennisclubRepository

    @Inject
    lateinit var playerRepository: PlayerRepository

    init {
        Application.appComponent.inject(this)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
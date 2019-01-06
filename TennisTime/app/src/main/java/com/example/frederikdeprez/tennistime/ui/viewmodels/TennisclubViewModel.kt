package com.example.frederikdeprez.tennistime.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.frederikdeprez.tennistime.data.Tennisclub

class TennisclubViewModel: BaseViewModel() {

    private val tennisclubName = MutableLiveData<String>()

    fun bind(tennisclub: Tennisclub) {
        tennisclubName.value = tennisclub.name
    }

    fun getTennisclubName(): MutableLiveData<String>{
        return tennisclubName
    }
}
package com.jossidfactory.beers.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.beers.model.Beer
import com.jossidfactory.beers.service.RetrofitHelper
import kotlinx.coroutines.launch

class DetailViewModel(id: String) : ViewModel() {
    private val retrofit = RetrofitHelper.getInstance()

    private val _beers = MutableLiveData<List<Beer>>()
    var beers: LiveData<List<Beer>> = _beers

    private var allBeers = mutableListOf<Beer>()

    init {
        onInit(id)
    }

    private fun onInit(id: String) {
        viewModelScope.launch {
            try {
                allBeers = retrofit.getBeerById(id).toMutableList()
                _beers.value = allBeers
            }catch (e: Exception) {

            }
        }
    }
}
package com.jossidfactory.beers.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.beers.model.Beer
import com.jossidfactory.beers.service.RetrofitHelper
import kotlinx.coroutines.launch

class HomeViewModel(): ViewModel() {
    private val retrofit = RetrofitHelper.getInstance()

    private val _searchValue = MutableLiveData<String>()
    val searchValue: LiveData<String> = _searchValue

    private val _filteredBeers = MutableLiveData<List<Beer>>()
    val filteredBeers: LiveData<List<Beer>> = _filteredBeers

    private var beers = mutableListOf<Beer>()

    init {
        onInit()
    }

    fun onInit() {
        viewModelScope.launch {
            try {
                beers = retrofit.getBeers().toMutableList()
                _filteredBeers.value = beers
            }catch (e: Exception) {

            }
        }
    }
    fun onSearchChange(searchValue: String) {
        _searchValue.value = searchValue
        if(searchValue.isNotEmpty()) {
            _filteredBeers.value = beers.filter { beer -> beer.name.lowercase().contains(searchValue.lowercase()) }
        } else {
            _filteredBeers.value = beers
        }
    }

}






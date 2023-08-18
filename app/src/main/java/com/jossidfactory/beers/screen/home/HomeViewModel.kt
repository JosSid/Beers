package com.jossidfactory.beers.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jossidfactory.beers.model.Beer
import com.jossidfactory.beers.service.RetrofitHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(): ViewModel() {
    private val retrofit = RetrofitHelper.getInstance()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoadig: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _searchValue = MutableLiveData<String>()
    val searchValue: LiveData<String> = _searchValue

    private val _filteredBeers = MutableLiveData<List<Beer>>()
    val filteredBeers: LiveData<List<Beer>> = _filteredBeers

    private var beers = mutableListOf<Beer>()

    init {
        onInit()
    }

    private fun onInit() {
        viewModelScope.launch {
            _error.value = false
            try {
                _isLoading.value = true
                delay(1500)
                beers = retrofit.getBeers().toMutableList()
                _filteredBeers.value = beers
            }catch (e: Exception) {
                _error.value = true
            }
            _isLoading.value = false
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

//Preguntar acerca de la factory
class HomeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}






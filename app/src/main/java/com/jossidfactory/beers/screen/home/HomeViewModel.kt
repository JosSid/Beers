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

    private val homeState = HomeState()

    private val _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState> = _state

    var beers = mutableListOf<Beer>()
        private set
    init {
        onInit()
    }

    private fun onInit() {
        _state.value = homeState
        viewModelScope.launch {
            _state.value = _state.value?.copy(
                error = true
            )
            try {
                _state.value = _state.value?.copy(
                    isLoading = true
                )
                delay(1500)
                beers = retrofit.getBeers().toMutableList()
                _state.value = _state.value?.copy(
                    filteredBeers = beers
                )
            }catch (e: Exception) {
                _state.value = _state.value?.copy(
                    error = true
                )
            }
            _state.value = _state.value?.copy(
                isLoading = false
            )
        }
    }
    fun onSearchChange(searchValue: String) {
        _state.value = _state.value?.copy(
            searchValue = searchValue
        )
        if (searchValue.isNotEmpty()) {
            _state.value = _state.value?.copy(
                filteredBeers = beers.filter { beer -> beer.name.lowercase().contains(searchValue.lowercase()) }
            )
        } else {
            _state.value = _state.value?.copy(
                filteredBeers = beers
            )
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






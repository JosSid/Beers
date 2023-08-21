package com.jossidfactory.beers.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.beers.data.ApiService
import com.jossidfactory.beers.domain.model.Beer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(val apiService: ApiService) : ViewModel() {

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
            try {
                _state.value = _state.value?.copy(
                    isLoading = true
                )
                delay(1000)
                beers = apiService.getBeers().toMutableList()
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







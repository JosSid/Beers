package com.jossidfactory.beers.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.beers.data.ApiService
import com.jossidfactory.beers.domain.model.Beer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailViewModel(val apiService: ApiService) : ViewModel() {

    private val detailState = DetailState()

    private val _state = MutableLiveData<DetailState>()
    val state: LiveData<DetailState> = _state

    private var responseBeers = mutableListOf<Beer>()

     fun getBeerById(id: String) {
        _state.value = detailState
        viewModelScope.launch {
            try {
                _state.value = _state.value?.copy(
                    isLoading = true
                )
                delay(1000)
                responseBeers = apiService.getBeerById(id).toMutableList()
                _state.value = _state.value?.copy( beer = responseBeers[0] )
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
}


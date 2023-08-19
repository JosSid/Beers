package com.jossidfactory.beers.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jossidfactory.beers.model.Beer
import com.jossidfactory.beers.service.RetrofitHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailViewModel(id: String) : ViewModel() {
    private val retrofit = RetrofitHelper.getInstance()

    private val detailState = DetailState()

    private val _state = MutableLiveData<DetailState>()
    val state: LiveData<DetailState> = _state

    private var responseBeers = mutableListOf<Beer>()

    init {
        onInit(id)
    }

    private fun onInit(id: String) {
        _state.value = detailState
        viewModelScope.launch {
            try {
                _state.value = _state.value?.copy(
                    isLoading = true
                )
                delay(1000)
                responseBeers = retrofit.getBeerById(id).toMutableList()
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

class DetailViewModelFactory(private val id: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

package com.jossidfactory.beers.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.beers.domain.model.BeerModel
import com.jossidfactory.beers.domain.usecase.GetBeerByIdUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailViewModel(private val getBeerByIdUseCase: GetBeerByIdUseCase) : ViewModel() {

    private val detailState = DetailState()

    private val _state = MutableLiveData<DetailState>()
    val state: LiveData<DetailState> = _state

    private var responseBeerModel = mutableListOf<BeerModel>()

     fun getBeerById(id: String) {
        _state.value = detailState
        viewModelScope.launch {
            try {
                _state.value = _state.value?.copy(
                    isLoading = true
                )
                delay(1000)
                responseBeerModel = getBeerByIdUseCase.invoke(id).toMutableList()
                _state.value = _state.value?.copy( beerDto = responseBeerModel[0] )
            }catch (e: Throwable) {
                _state.value = _state.value?.copy(
                    error = e.message.toString()
                )
            }
            _state.value = _state.value?.copy(
                isLoading = false
            )
        }
    }
}


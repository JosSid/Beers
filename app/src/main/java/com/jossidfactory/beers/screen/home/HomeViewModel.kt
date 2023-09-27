package com.jossidfactory.beers.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.beers.domain.model.BeerModel
import com.jossidfactory.beers.domain.usecase.GetBeersListUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(private val getBeersListUseCase: GetBeersListUseCase) : ViewModel() {

    private val homeState = HomeState()

    private val _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState> = _state

    var beerModels = mutableListOf<BeerModel>()
        private set
    init {
        onInit()
    }

    fun onInit() {
        _state.value = homeState
        viewModelScope.launch {
            try {
                _state.value = _state.value?.copy(
                    isLoading = true
                )
                delay(1000)
                beerModels = getBeersListUseCase.invoke().toMutableList()
                _state.value = _state.value?.copy(
                    filteredBeerDtos = beerModels
                )
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
    fun onSearchChange(searchValue: String) {
        _state.value = _state.value?.copy(
            searchValue = searchValue
        )
        if (searchValue.isNotEmpty()) {
            _state.value = _state.value?.copy(
                filteredBeerDtos = beerModels.filter { beer -> beer.name.lowercase().contains(searchValue.lowercase())
                     }
            )
        } else {
            _state.value = _state.value?.copy(
                filteredBeerDtos = beerModels
            )
        }
    }
}







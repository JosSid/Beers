package com.jossidfactory.beers.di

import com.jossidfactory.beers.domain.usecase.GetBeerByIdUseCase
import com.jossidfactory.beers.domain.usecase.GetBeersListUseCase
import org.koin.dsl.module

val DomainModule = module {
    single { GetBeersListUseCase(get()) }
    single { GetBeerByIdUseCase(get()) }
}


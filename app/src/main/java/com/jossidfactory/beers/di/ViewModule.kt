package com.jossidfactory.beers.di

import com.jossidfactory.beers.screen.detail.DetailViewModel
import com.jossidfactory.beers.screen.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
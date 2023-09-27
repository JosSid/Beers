package com.jossidfactory.beers.screen.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jossidfactory.beers.domain.model.BeerModel
import com.jossidfactory.beers.domain.usecase.GetBeersListUseCase
import com.jossidfactory.beers.testutil.DefaultDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val defaultDispatcherRule: DefaultDispatcherRule = DefaultDispatcherRule()

    @RelaxedMockK
    private lateinit var getBeersListUseCase: GetBeersListUseCase

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        homeViewModel = HomeViewModel(getBeersListUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `WHEN viewModel is init EXPECT data at LiveData`() = runTest {
        val beers = mutableListOf(
            BeerModel(
                id = 1,
                name = "Beer1",
                description = "Beer1 description",
                imageUrl = "image1",
                abv = 1.00
            ),
            BeerModel(
                id = 2,
                name = "Beer2",
                description = "Beer2 description",
                imageUrl = "image2",
                abv = 2.00
            )
        )
        //Given
        coEvery { getBeersListUseCase.invoke() } returns beers
        //When
        homeViewModel.onInit()

        //Then
        assertThat(homeViewModel.state, notNullValue())
        advanceTimeBy(1001)
        assertThat(homeViewModel.beerModels,equalTo(beers))
    }
}
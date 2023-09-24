package com.jossidfactory.beers.domain.usecase

import com.jossidfactory.beers.data.BeersRepository
import com.jossidfactory.beers.domain.model.BeerModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetBeersListUseCaseTest {

    @RelaxedMockK
    private lateinit var beersRepository: BeersRepository

    private lateinit var getBeersListUseCase: GetBeersListUseCase

    private val beersList = listOf(
        BeerModel(
            id = 1,
            name = "Beer",
            description = "BeerModel",
            imageUrl = "",
            abv = 1.00
        ),
        BeerModel(
            id = 2,
            name = "Beer2",
            description = "BeerModel2",
            imageUrl = "",
            abv = 1.00
        ),
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this)
       getBeersListUseCase = GetBeersListUseCase(beersRepository)
    }

    @Test
    fun `WHEN getBeersListUseCase is called RETURN a list of beers`() = runBlocking{


        //Given
            coEvery { beersRepository.getBeers() } returns beersList

        //When
            val result = getBeersListUseCase.invoke()

        //Then
            coVerify(exactly = 1) { beersRepository.getBeers() }
            assert(result == beersList)
    }
}
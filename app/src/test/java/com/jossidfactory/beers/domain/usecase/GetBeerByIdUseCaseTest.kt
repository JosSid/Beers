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

class GetBeerByIdUseCaseTest {

    @RelaxedMockK
    private lateinit var beersRepository: BeersRepository

    private lateinit var getBeerByIdUseCase: GetBeerByIdUseCase

    private val beersList = listOf(
        BeerModel(
            id = 1,
            name = "Beer",
            description = "BeerModel",
            imageUrl = "",
            abv = 1.00
        )
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getBeerByIdUseCase = GetBeerByIdUseCase(beersRepository)
    }

    @Test
    fun `WHEN getBeerByIdUseCase is called return a list of beers with size == 1`() = runBlocking{
        val beerId = 1.toString()
        //Given
        coEvery { beersRepository.getBeerById(beerId) } returns beersList

        //When
        val beer = getBeerByIdUseCase.invoke(beerId)

        //Then
        coVerify(exactly = 1) { beersRepository.getBeerById(beerId) }

        assert(beer.size == 1)

        assert(beerId.toInt() == beer.first().id)
    }
}
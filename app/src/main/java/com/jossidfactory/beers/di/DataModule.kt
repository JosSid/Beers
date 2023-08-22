package com.jossidfactory.beers.di

import android.content.Context
import androidx.room.Room
import com.jossidfactory.beers.BuildConfig
import com.jossidfactory.beers.data.ApiService
import com.jossidfactory.beers.data.BeersRepository
import com.jossidfactory.beers.data.BeersRepositoryImpl
import com.jossidfactory.beers.data.local.BeerDao
import com.jossidfactory.beers.data.local.BeersDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val DataModule = module {
    single<Retrofit> {
        val URL = BuildConfig.SERVER_URL

        Retrofit
            .Builder()
            .baseUrl(URL) // Base URL de la API
            .addConverterFactory(MoshiConverterFactory.create(get())) // Conversor JSON
            .build()
    }

    single<Moshi> {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    single<ApiService> {
        getDataApi(get())
    }

    single<BeersRepository> {
        BeersRepositoryImpl(get(), get())
    }

    single {
        getDatabase(get())
    }

    single {
        providesBeerDao(get())
    }
}

private fun getDataApi(retrofit: Retrofit) =
    retrofit.create(ApiService::class.java)

private fun getDatabase(context: Context) : BeersDatabase =
    Room.databaseBuilder(
        context,
        BeersDatabase::class.java, "beers-db"
    ).fallbackToDestructiveMigration().build()

private fun providesBeerDao(db: BeersDatabase) : BeerDao =
    db.beerDao()
package com.jossidfactory.beers.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jossidfactory.beers.data.local.model.BeerLocal

@Database(entities = [BeerLocal::class], version = 1)
abstract class BeersDatabase: RoomDatabase() {
    abstract fun beerDao(): BeerDao
}
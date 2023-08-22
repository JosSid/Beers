package com.jossidfactory.beers.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jossidfactory.beers.data.local.model.BeerLocal

@Dao
interface BeerDao {
    @Query("SELECT * FROM beers")
     suspend fun getBeers(): List<BeerLocal>

    @Query("SELECT * FROM beers WHERE id=:id")
    suspend fun getBeerById(id: String) : List<BeerLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBeers(list: List<BeerLocal>)
}
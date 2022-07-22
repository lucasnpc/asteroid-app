package com.udacity.asteroidradar.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.domain.model.Asteroid

@Dao
interface AsteroidDao {

    @Query("SELECT * FROM Asteroid")
    fun getAsteroids(): List<Asteroid>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAsteroid(vararg asteroid: Asteroid)
}
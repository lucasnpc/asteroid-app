package com.udacity.asteroidradar.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.udacity.asteroidradar.domain.model.Asteroid
import kotlinx.coroutines.flow.Flow

@Dao
interface AsteroidDao {

    @Query("SELECT * FROM Asteroid")
    fun getAsteroids(): Flow<List<Asteroid>>

    @Insert
    fun insertAsteroid(vararg asteroid: Asteroid)
}
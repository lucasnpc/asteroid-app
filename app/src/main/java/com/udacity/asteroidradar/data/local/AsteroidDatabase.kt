//package com.udacity.asteroidradar.data.local
//
//import androidx.room.Database
//import androidx.room.RoomDatabase
//import com.udacity.asteroidradar.data.local.dao.AsteroidDao
//import com.udacity.asteroidradar.domain.model.Asteroid
//
//@Database(entities = [Asteroid::class], version = 1)
//abstract class AsteroidDatabase : RoomDatabase() {
//    companion object {
//        const val DATABASE_NAME = "asteroids_app_db"
//    }
//
//    abstract val asteroidDao: AsteroidDao
//}
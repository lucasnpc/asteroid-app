package com.udacity.asteroidradar.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import androidx.room.Room
import com.udacity.asteroidradar.data.local.AsteroidDatabase
import com.udacity.asteroidradar.data.local.dao.AsteroidDao
import com.udacity.asteroidradar.domain.model.Asteroid
import com.udacity.asteroidradar.util.Constants.AUTHORITY

private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
    addURI(AUTHORITY, "asteroids", 1)
}

class AsteroidProvider : ContentProvider() {

    private var asteroidDatabase: AsteroidDatabase? = null
    private var asteroidDao: AsteroidDao? = null

    override fun onCreate(): Boolean {
        context?.let {
            asteroidDatabase = Room.databaseBuilder(
                it,
                AsteroidDatabase::class.java,
                AsteroidDatabase.DATABASE_NAME
            ).build()
        }

        asteroidDao = asteroidDatabase?.asteroidDao

        return asteroidDatabase != null
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor {
        val cursor = MatrixCursor(
            listOf(
                "_id",
                "codename",
                "closeApproachDate",
                "absoluteMagnitude",
                "estimatedDiameter",
                "relativeVelocity",
                "distanceFromEarth",
                "isPotentiallyHazardous"
            ).toTypedArray()
        )
        when (uriMatcher.match(uri)) {
            1 -> {
                asteroidDao?.getAsteroids()?.forEach {
                    cursor.newRow().apply {
                        add("_id", it.id)
                        add("codename", it.codename)
                        add("closeApproachDate", it.closeApproachDate)
                        add("absoluteMagnitude", it.absoluteMagnitude)
                        add("estimatedDiameter", it.estimatedDiameter)
                        add("relativeVelocity", it.relativeVelocity)
                        add("distanceFromEarth", it.distanceFromEarth)
                        add("isPotentiallyHazardous", it.isPotentiallyHazardous)
                    }
                }
            }
            else -> {}
        }
        cursor.setNotificationUri(context?.contentResolver, uri)
        return cursor
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }
}
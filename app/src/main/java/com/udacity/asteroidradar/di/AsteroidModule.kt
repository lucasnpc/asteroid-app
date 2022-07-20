package com.udacity.asteroidradar.di

import android.app.Application
import androidx.room.Room
import com.udacity.asteroidradar.data.AsteroidRadarRepositoryImpl
import com.udacity.asteroidradar.data.api.AsteroidApiService
import com.udacity.asteroidradar.data.local.AsteroidDatabase
import com.udacity.asteroidradar.domain.AsteroidRadarUseCases
import com.udacity.asteroidradar.domain.data.AsteroidRadarRepository
import com.udacity.asteroidradar.domain.useCase.GetAsteroidUseCase
import com.udacity.asteroidradar.domain.useCase.GetImageOfDayUseCase
import com.udacity.asteroidradar.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AsteroidModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): AsteroidDatabase =
        Room.databaseBuilder(app, AsteroidDatabase::class.java, AsteroidDatabase.DATABASE_NAME)
            .build()

    @Provides
    @Singleton
    fun provideService(): AsteroidApiService =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AsteroidApiService::class.java)

    @Provides
    @Singleton
    fun provideRepository(
        service: AsteroidApiService,
        database: AsteroidDatabase
    ): AsteroidRadarRepository = AsteroidRadarRepositoryImpl(service, database)

    @Provides
    @Singleton
    fun provideUseCases(repository: AsteroidRadarRepository): AsteroidRadarUseCases =
        AsteroidRadarUseCases(
            getImageOfDayUseCase = GetImageOfDayUseCase(repository),
            getAsteroidUseCase = GetAsteroidUseCase(repository)
        )
}
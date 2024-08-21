package com.example.mybrickset.di

import android.app.Application
import com.example.mybrickset.Const.BASE_URL
import com.example.mybrickset.data.local.datastore.AuthPreferences
import com.example.mybrickset.data.remote.BricksetApi
import com.example.mybrickset.data.repository.BricksetRepositoryImplementation
import com.example.mybrickset.domain.BricksetRepository
import com.example.mybrickset.domain.usecase.BricksetUseCases
import com.example.mybrickset.domain.usecase.GetNewReleasedSets
import com.example.mybrickset.domain.usecase.GetSetsByTheme
import com.example.mybrickset.domain.usecase.GetThemes
import com.example.mybrickset.domain.usecase.Login
import com.example.mybrickset.domain.usecase.SearchSets
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBricksetAPi(): BricksetApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val authInterceptor = Interceptor { chain ->
            val req = chain.request()
            val requestHeaders = req.newBuilder()
                .addHeader("Authorization", BASE_URL)
                .build()
            chain.proceed(requestHeaders)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(BricksetApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBricksetRepository(
        api: BricksetApi
    ): BricksetRepository {
        return BricksetRepositoryImplementation(api)
    }

    @Provides
    @Singleton
    fun provideBricksetUseCase(
        bricksetRepository: BricksetRepository
    ): BricksetUseCases {
        return BricksetUseCases(
            getNewReleasedSets = GetNewReleasedSets(bricksetRepository),
            getSetsByTheme = GetSetsByTheme(bricksetRepository),
            getThemes = GetThemes(bricksetRepository),
            searchSets = SearchSets(bricksetRepository),
            login = Login(bricksetRepository)
        )
    }

    @Provides
    @Singleton
    fun provideAuthPreferences(application: Application): AuthPreferences {
        return AuthPreferences(application.applicationContext)
    }
}
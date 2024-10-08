package com.example.mybrickset.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.mybrickset.BuildConfig
import com.example.mybrickset.Const.DATABASE_NAME
import com.example.mybrickset.data.local.CollectionDao
import com.example.mybrickset.data.local.CollectionDatabase
import com.example.mybrickset.data.local.datastore.AuthPreferences
import com.example.mybrickset.data.remote.BricksetApi
import com.example.mybrickset.data.repository.BricksetRepositoryImplementation
import com.example.mybrickset.data.repository.LocalRepositoryImplementation
import com.example.mybrickset.domain.BricksetRepository
import com.example.mybrickset.domain.LocalRepository
import com.example.mybrickset.domain.usecase.BricksetUseCases
import com.example.mybrickset.domain.usecase.GetAdditionalImage
import com.example.mybrickset.domain.usecase.GetNewReleasedSets
import com.example.mybrickset.domain.usecase.GetReviews
import com.example.mybrickset.domain.usecase.GetSetById
import com.example.mybrickset.domain.usecase.GetSetsByTheme
import com.example.mybrickset.domain.usecase.GetSetsOwned
import com.example.mybrickset.domain.usecase.GetSetsWanted
import com.example.mybrickset.domain.usecase.GetThemes
import com.example.mybrickset.domain.usecase.GetUserNotes
import com.example.mybrickset.domain.usecase.Login
import com.example.mybrickset.domain.usecase.SearchSets
import com.example.mybrickset.domain.usecase.SetCollectionNotes
import com.example.mybrickset.domain.usecase.SetCollectionOwned
import com.example.mybrickset.domain.usecase.SetCollectionRating
import com.example.mybrickset.domain.usecase.SetCollectionWanted
import com.example.mybrickset.domain.usecase.local.DeleteSetColleciton
import com.example.mybrickset.domain.usecase.local.GetAllSetCollection
import com.example.mybrickset.domain.usecase.local.GetAllSetCollectionOrderByDateAsc
import com.example.mybrickset.domain.usecase.local.GetAllSetCollectionOrderByDateDesc
import com.example.mybrickset.domain.usecase.local.GetAllSetCollectionOrderByPriceAsc
import com.example.mybrickset.domain.usecase.local.GetAllSetCollectionOrderByPriceDesc
import com.example.mybrickset.domain.usecase.local.GetSetCount
import com.example.mybrickset.domain.usecase.local.GetSumPrice
import com.example.mybrickset.domain.usecase.local.InsertSetCollection
import com.example.mybrickset.domain.usecase.local.LocalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
                .addHeader("Authorization", BuildConfig.BASE_URL)
                .build()
            chain.proceed(requestHeaders)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(BricksetApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBricksetRepository(
        api: BricksetApi,
        preferences: AuthPreferences
    ): BricksetRepository {
        return BricksetRepositoryImplementation(api, preferences)
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
            login = Login(bricksetRepository),
            getAdditionalImage = GetAdditionalImage(bricksetRepository),
            getSetById = GetSetById(bricksetRepository),
            getReviews = GetReviews(bricksetRepository),
            setCollectionWanted = SetCollectionWanted(bricksetRepository),
            setCollectionOwned = SetCollectionOwned(bricksetRepository),
            setCollectionNotes = SetCollectionNotes(bricksetRepository),
            setCollectionRating = SetCollectionRating(bricksetRepository),
            getSetsWanted = GetSetsWanted(bricksetRepository),
            getSetsOwned = GetSetsOwned(bricksetRepository),
            getUserNotes = GetUserNotes(bricksetRepository)
        )
    }

    @Provides
    @Singleton
    fun provideAuthPreferences(application: Application): AuthPreferences {
        return AuthPreferences(application.applicationContext)
    }

    @Provides
    @Singleton
    fun provideCollectionDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, CollectionDatabase::class.java, DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideCollectionDao(
        database: CollectionDatabase
    ) = database.CollectionDao()

    @Provides
    @Singleton
    fun provideLocalRepository(
        dao: CollectionDao
    ): LocalRepository {
        return LocalRepositoryImplementation(dao)
    }

    @Provides
    @Singleton
    fun provideLocalUseCase(
        localRepository: LocalRepository
    ): LocalUseCase {
        return LocalUseCase(
            insertSetCollection = InsertSetCollection(localRepository),
            deleteSetCollection = DeleteSetColleciton(localRepository),
            getAllSetCollection = GetAllSetCollection(localRepository),
            getAllSetCollectionOrderByDateAsc = GetAllSetCollectionOrderByDateAsc(localRepository),
            getAllSetCollectionOrderByDateDesc = GetAllSetCollectionOrderByDateDesc(localRepository),
            getAllSetCollectionOrderByPriceAsc = GetAllSetCollectionOrderByPriceAsc(localRepository),
            getAllSetCollectionOrderByPriceDesc = GetAllSetCollectionOrderByPriceDesc(localRepository),
            getSetCount = GetSetCount(localRepository),
            getSumPrice = GetSumPrice(localRepository)
        )
    }


}
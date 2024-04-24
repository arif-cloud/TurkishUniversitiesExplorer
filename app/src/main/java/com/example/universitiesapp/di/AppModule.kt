package com.example.universitiesapp.di

import android.content.Context
import androidx.room.Room
import com.example.universitiesapp.common.Constants
import com.example.universitiesapp.data.local.db.UniversityDatabase
import com.example.universitiesapp.data.local.repository.FavoriteUniversityRepositoryImpl
import com.example.universitiesapp.data.remote.api.UniversityApi
import com.example.universitiesapp.data.repository.UniversityRepositoryImpl
import com.example.universitiesapp.domain.repository.FavoriteUniversityRepository
import com.example.universitiesapp.domain.repository.UniversityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
    fun provideUniversityApi() : UniversityApi {
        return Retrofit.Builder().
        baseUrl(Constants.BASE_URL).
        client(OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()).
        addConverterFactory(GsonConverterFactory.create()).
        build().
        create(UniversityApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUniversityRepository(api: UniversityApi) : UniversityRepository {
        return UniversityRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUniversityDatabase(@ApplicationContext context: Context) : UniversityDatabase {
        return Room.databaseBuilder(context, UniversityDatabase::class.java, "universities").allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideFavoriteUniversityRepository(database: UniversityDatabase) : FavoriteUniversityRepository {
        return FavoriteUniversityRepositoryImpl(database.UniversitiesDao())
    }

}
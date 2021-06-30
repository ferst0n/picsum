package com.example.picsum.di

import android.content.Context
import androidx.room.Room
import com.example.picsum.data.api.Service
import com.example.picsum.data.api.retrofit
import com.example.picsum.data.db.AppDataBase
import com.example.picsum.data.db.ImageDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() = retrofit.provideRetrofit()

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): Service =
        retrofit.create(Service::class.java)

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context) =
        AppDataBase.getDatabase(context)
}


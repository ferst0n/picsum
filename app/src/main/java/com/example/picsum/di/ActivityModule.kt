package com.example.picsum.di

import com.example.picsum.data.api.Service
import com.example.picsum.data.db.AppDataBase
import com.example.picsum.data.repository.ImageRepository
import com.example.picsum.paging.LocalPagingSource
import com.example.picsum.paging.RemotePagingSource
import com.example.picsum.ui.RandomPhotoList.ItemComparator
import com.example.picsum.ui.RandomPhotoList.RandomPhotoListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {


    @Provides
    fun provideRemotePagingSource(service: Service) =
            RemotePagingSource(service)

    @Provides
    fun provideLocalPagingSource(appDatabase: AppDataBase) =
            LocalPagingSource(appDatabase)

    @Provides
    fun provideRandomPhotoListAdapter() =
            RandomPhotoListAdapter(ItemComparator)

    @Provides
    fun provideImageRepository(service: Service, appDatabase: AppDataBase)=
        ImageRepository(service, appDatabase)


}

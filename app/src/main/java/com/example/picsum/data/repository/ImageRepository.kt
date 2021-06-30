package com.example.picsum.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.picsum.data.api.Service
import com.example.picsum.data.db.AppDataBase
import com.example.picsum.data.model.Image
import com.example.picsum.paging.LocalPagingSource
import com.example.picsum.paging.RemotePagingSource
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class ImageRepository@Inject constructor(private val service: Service,private val appDatabase: AppDataBase):Repository{


    fun getRemoteData(): Flow<PagingData<Image>> {
        return Pager(
                config =  PagingConfig(5),
                pagingSourceFactory = {RemotePagingSource(service)}
        ).flow

    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertImage(image: Image) {
        appDatabase.imageDao().insertImage(image)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteImage(image: Image){
        appDatabase.imageDao().deleteImage(image)
    }

    fun getLocalData(): Flow<PagingData<Image>>{

        return Pager(
            config =  PagingConfig(5),
            pagingSourceFactory = {LocalPagingSource(appDatabase)}
        ).flow

    }






}
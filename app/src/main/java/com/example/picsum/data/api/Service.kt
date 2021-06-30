package com.example.picsum.data.api

import com.example.picsum.data.model.Image
import com.example.picsum.data.model.ImageList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("v2/list")
    suspend fun getPhoto(@Query("page")page:Int,
                         @Query("limit")limit:Int
    ):Response<List<Image>>
    
}
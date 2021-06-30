package com.example.picsum.data.db

import androidx.paging.PagingSource
import androidx.room.*
import com.example.picsum.data.model.Image
import kotlinx.coroutines.flow.Flow
import retrofit2.http.DELETE


@Dao
interface ImageDao {

    @Query("SELECT * FROM images")
    suspend fun getImages(): List<Image>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image:Image)

    @Delete
    suspend fun deleteImage(image: Image)


}
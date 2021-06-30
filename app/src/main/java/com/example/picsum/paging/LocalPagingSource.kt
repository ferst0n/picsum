package com.example.picsum.paging


import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.picsum.data.api.Service
import com.example.picsum.data.db.AppDataBase
import com.example.picsum.data.model.Image
import com.example.picsum.data.utils.util
import com.google.gson.GsonBuilder

class LocalPagingSource(val appDataBase: AppDataBase) : PagingSource<Int, Image>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        try {

            val page:Int = params.key ?: 1
            val pageSize:Int = params.loadSize


            val result= appDataBase.imageDao().getImages()

            return LoadResult.Page(
                data = result,
                prevKey = null,
                nextKey = if(result.size<pageSize) null else page + 1
            )
        } catch (e: Throwable) {
            Log.e("Error", "Item Page Source", e)
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        return state.anchorPosition
    }

}
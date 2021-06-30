package com.example.picsum.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.picsum.data.api.Service
import com.example.picsum.data.model.Image
import com.example.picsum.data.utils.util
import com.google.gson.GsonBuilder

class RemotePagingSource(val service: Service ) : PagingSource<Int, Image>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        try {

            val page:Int = params.key ?: getPageNumber()
            val pageSize:Int = params.loadSize

            val response = service.getPhoto(page,pageSize)

            val gson = GsonBuilder().setPrettyPrinting().create()
            val Json = gson.toJson(response.body())
            Log.d("Printed JSON :", Json)

            val result= response.body()

            return LoadResult.Page(
                    data = result!!,
                    prevKey = null,
                    nextKey = getPageNumber()
            )
        } catch (e: Throwable) {
            Log.e("Error", "Item Page Source", e)
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        return state.anchorPosition
    }

    companion object{
        fun getFirstPageNumber(): MutableList<Int> {
            var list: MutableList<Int> = ArrayList()
            for(i in 1..199){
                list.add(i)
            }
            list.shuffle()
            println(list)
            return list
        }
        var shuffledList: MutableList<Int> = getFirstPageNumber()
        fun getPageNumber(): Int {

            var page = 0

            for(i in 0..0){
                page = shuffledList[i]
                println(page)
                shuffledList.remove(page)
            }
            println(shuffledList)
            return page
        }
    }
}
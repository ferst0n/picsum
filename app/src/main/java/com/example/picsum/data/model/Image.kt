package com.example.picsum.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class ImageList(
        val results:List<Image>
)

@Entity(tableName = "images")
data class Image(
        @PrimaryKey
        val id:String,
        @ColumnInfo(name = "author")
        val author:String?,
        @ColumnInfo(name = "width")
        val width:Int,
        @ColumnInfo(name = "height")
        val height:Int,
        @ColumnInfo(name = "url")
        val url:String,
        @ColumnInfo(name = "download_url")
        val download_url: String

)



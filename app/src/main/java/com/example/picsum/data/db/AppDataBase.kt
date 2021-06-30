package com.example.picsum.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.picsum.data.model.Image


@Database(entities = arrayOf(Image::class), version = 1)
abstract class AppDataBase : RoomDatabase(){

    abstract fun imageDao(): ImageDao

    companion object{
        @Volatile private var instance:AppDataBase? = null
        fun getDatabase(context: Context):AppDataBase =
                instance?: synchronized(this){
                    instance?: buildDatabase(context).apply { instance = this }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context,AppDataBase::class.java,"images.db")
                        .fallbackToDestructiveMigration()
                        .build()
    }
}
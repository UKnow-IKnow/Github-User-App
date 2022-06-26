package com.example.githubapp.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(
    entities = [FavUser::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {
    companion object{
        var INSTANCE : UserDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): UserDatabase?{
            if(INSTANCE==null){
                synchronized(UserDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, "User database").build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun favUserDao(): FavDao
}
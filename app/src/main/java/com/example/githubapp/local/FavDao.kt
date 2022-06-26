package com.example.githubapp.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FavDao {
    @Insert
    suspend fun addToFav(FavUser: FavUser)

    @Query("SELECT * FROM favorite_User")
    fun getFavUser(): LiveData<List<FavUser>>

    @Query("SELECT count(*) FROM favorite_User WHERE favorite_User.id = :id")
    suspend fun checkUser(id:Int): Int

    @Query("DELETE FROM favorite_User WHERE favorite_User.id = :id")
    suspend fun removeFromFav(id: Int): Int
}
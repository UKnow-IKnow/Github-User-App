package com.example.githubapp.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favorite_User")
data class FavUser(
    val login: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int
): Serializable

package com.example.githubapp.api

import com.example.githubapp.data.models.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("users")
    fun getSearchUsers(
        @Query("") query: String
    ): Call<UserResponse>
}
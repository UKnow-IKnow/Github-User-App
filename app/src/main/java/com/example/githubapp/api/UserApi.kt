package com.example.githubapp.api

import com.example.githubapp.data.models.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface UserApi {

    @GET("users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("") query: String
    ): Call<UserResponse>
}
package com.example.githubapp.api

import com.example.githubapp.data.models.UserResponse
import com.example.githubapp.data.models.UserResponseItem
import com.example.githubapp.data.models.detailUserResponse
import retrofit2.Call
import retrofit2.http.*

interface UserApi {

    @GET("users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<detailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<UserResponseItem>>


    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<UserResponseItem>>


}
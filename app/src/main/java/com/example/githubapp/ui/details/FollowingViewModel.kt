package com.example.githubapp.ui.details

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.api.RetrofitClient
import com.example.githubapp.data.models.UserResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {
    val listFollower = MutableLiveData<ArrayList<UserResponseItem>>()

    fun setListFollowing(username: String) {
        RetrofitClient.apiInstance
            .getFollowing(username)
            .enqueue(object : Callback<ArrayList<UserResponseItem>> {
                override fun onResponse(
                    call: Call<ArrayList<UserResponseItem>>,
                    response: Response<ArrayList<UserResponseItem>>
                ) {
                    if (response.isSuccessful) {
                        listFollower.postValue(response.body())
                    } else {
                        Log.d(ContentValues.TAG, "onResponse: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<ArrayList<UserResponseItem>>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }
            })
    }

    fun getListFollowing(): LiveData<ArrayList<UserResponseItem>> {
        return listFollower
    }
}
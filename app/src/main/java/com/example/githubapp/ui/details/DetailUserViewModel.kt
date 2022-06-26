package com.example.githubapp.ui.details

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.api.RetrofitClient
import com.example.githubapp.data.models.detailUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {
    val user = MutableLiveData<detailUserResponse>()

    fun setUseDetail(username: String) {
        RetrofitClient.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<detailUserResponse> {
                override fun onResponse(
                    call: Call<detailUserResponse>,
                    response: Response<detailUserResponse>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    } else {
                        Log.d(ContentValues.TAG, "onResponse: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<detailUserResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }
            })
    }
    fun getUserDetail(): LiveData<detailUserResponse>{
        return user
    }
}
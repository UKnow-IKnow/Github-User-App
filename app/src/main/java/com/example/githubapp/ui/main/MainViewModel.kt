package com.example.githubapp.ui.main

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.api.RetrofitClient
import com.example.githubapp.data.models.UserResponse
import com.example.githubapp.data.models.UserResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val listUser = MutableLiveData<ArrayList<UserResponseItem>>()

    fun setSearchUser(query: String){
        RetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<ArrayList<UserResponseItem>>{
                override fun onResponse(
                    call: Call<ArrayList<UserResponseItem>>,
                    response: Response<ArrayList<UserResponseItem>>
                ) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            listUser.postValue(it)
                        }
                    }else{
                        Log.d(TAG,"onResponse: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<ArrayList<UserResponseItem>>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }
            })
    }

    fun getSearchUsers(): LiveData<ArrayList<UserResponseItem>>{
        return listUser
    }
}
package com.example.githubapp.ui.details

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.api.RetrofitClient
import com.example.githubapp.data.models.detailUserResponse
import com.example.githubapp.local.FavDao
import com.example.githubapp.local.FavUser
import com.example.githubapp.local.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application) : AndroidViewModel(application) {
    val user = MutableLiveData<detailUserResponse>()

    private var userDao: FavDao?
    private var userDb: UserDatabase?

    init {
        userDb = UserDatabase.getDatabase(application)
        userDao = userDb?.favUserDao()
    }

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

    fun addToFav(username: String, id:Int, avatar_url: String){
        CoroutineScope(Dispatchers.IO).launch {
            var user = FavUser(
                username,
                id,
                avatar_url
            )
            userDao?.addToFav(user)
        }
    }

    suspend fun checkUser(id: Int) = userDao?.checkUser(id)

    fun removeFromFav(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.removeFromFav(id)
        }
    }
}
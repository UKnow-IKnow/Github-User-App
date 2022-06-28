package com.example.githubapp.ui.fav

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.githubapp.local.FavDao
import com.example.githubapp.local.FavUser
import com.example.githubapp.local.UserDatabase

class FavViewModel(application: Application): AndroidViewModel(application) {

    private var userDao: FavDao?
    private var userDb: UserDatabase?

    init {
        userDb = UserDatabase.getDatabase(application)
        userDao = userDb?.favUserDao()
    }

    fun getFavUser(): LiveData<List<FavUser>>?{
        return userDao?.getFavUser()
    }
}
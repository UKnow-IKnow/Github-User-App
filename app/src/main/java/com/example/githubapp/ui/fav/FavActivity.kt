package com.example.githubapp.ui.fav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.adapters.UserAdapter
import com.example.githubapp.data.models.UserResponseItem
import com.example.githubapp.databinding.ActivityFavBinding
import com.example.githubapp.local.FavUser
import com.example.githubapp.ui.details.DetailUserActivity

class FavActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: FavViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavViewModel::class.java)

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: UserResponseItem) {
                Intent(this@FavActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailUserActivity.EXTRA_URL, data.avatar_url)
                    startActivity(it)
                }
            }
        })

        binding.apply {
            rvFUser.setHasFixedSize(true)
            rvFUser.layoutManager = LinearLayoutManager(this@FavActivity)
            rvFUser.adapter = adapter
        }

        viewModel.getFavUser()?.observe(this, {
            if (it!=null){
                val list = mapList(it)
                adapter.setList(list)
            }
        })
    }

    private fun mapList(users: List<FavUser>): ArrayList<UserResponseItem> {
        val listUser = ArrayList<UserResponseItem>()
        for(user in users){
            val userMapped = UserResponseItem(
                user.login,
                user.id,
                user.avatar_url
            )
            listUser.add(userMapped)
        }
        return listUser
    }
}
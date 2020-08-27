package com.example.restapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.restapi.pojo.Post
import com.example.restapi.pojo.User
import com.example.restapi.remote.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

   // private var postsList =  ArrayList<Post>()
   // private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private var userList =  ArrayList<User>()
    private lateinit var viewAdapter: AdapterUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /* viewAdapter = PostAdapter(postsList)
        postsRecyclerView.adapter = viewAdapter

        loadApiData()*/




        viewAdapter = AdapterUser(userList)
        postsRecyclerView.adapter = viewAdapter
       

        loadApiData()
    }

    private fun loadApiData(){
        val service=RetrofitClient.retrofitInstance()
        val call=service.getAllUsers()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                response.body()?.map { userList.add(it) }
                viewAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Error: no se logro recuperar los usuarios desde la api",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }

   /* private fun loadApiData() {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getAllPosts()

       *//* call.enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(
                call: Call<ArrayList<Post>>,
                response: Response<ArrayList<Post>>
            ) {
                response.body()?.map {
                    Log.d("MAIN", "${it.id} - ${it.title}")
                    postsList.add(it)
                }
                viewAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                Log.d("MAIN", "Error: " + t)
                Toast.makeText(
                    applicationContext,
                    "Error: no logramos recuperar los posts desde el api",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })*//*
    }*/
}
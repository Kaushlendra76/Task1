package com.example.task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {


    lateinit var myadapter : MyAdapter
    lateinit var  myrecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myrecyclerView = findViewById(R.id.recyclerView)

        val  RetrofitBuilder = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitInterface :: class.java)

        val retrofidata = RetrofitBuilder.getUserData()

        retrofidata.enqueue(object : Callback<ApiData?> {
            override fun onResponse(call: Call<ApiData?>, response: Response<ApiData?>) {
                val responsebody = response.body()
                val userlist = responsebody?.data!!

                myadapter = MyAdapter(this@MainActivity, userlist)
                myrecyclerView.adapter = myadapter
                myrecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<ApiData?>, t: Throwable) {
                Log.d("Main Activity", "onFailure" + t.message)
            }
        })






    }
}
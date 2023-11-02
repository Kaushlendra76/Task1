package com.example.task1

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("users")
    fun getUserData() : Call<ApiData>
}
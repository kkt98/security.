package com.example.security.network

import com.example.security.User
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitApi {

    @FormUrlEncoded
    @POST("/join")
    fun singUp(@FieldMap dataPart: Map<String, String>): Call<User>

}
package com.data

import com.data.model.ResponseNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiServices {

    @GET("everything")
    suspend fun getNews(@QueryMap queries:Map<String,String>):Response<ResponseNews>

}
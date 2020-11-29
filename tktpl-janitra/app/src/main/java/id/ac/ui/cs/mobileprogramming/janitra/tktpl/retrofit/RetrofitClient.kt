package id.ac.ui.cs.mobileprogramming.janitra.tktpl.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val BASE_URL =  "https://98f92bfcc9496071242760fddf22ed4a.m.pipedream.net"

    private val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val RETROFIT_SERVICE: ApiService = client.create(ApiService::class.java)
}
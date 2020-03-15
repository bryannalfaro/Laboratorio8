package com.example.laboratorio8.redes

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Api for the app
 * @author Bryann Alfaro
 */
/**
 * Variables
 */
private val BASE_URL="https://api.github.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Init the retrofit variable
 */
private val retrofit=Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(
    moshi)).baseUrl(
    BASE_URL).build()


interface ApiService {


    @GET("/users/{valors}")
    fun getProperties(@Path("valors") valors: String?):
            Call<GitPropertyClass>

    @GET("/users/{valors}/repos")
    fun getPropertiesRepo(@Path("valors") valors: String?):
            Call<List<ReposProperty>>
}

object ApiServices{
    val retrofitService:ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
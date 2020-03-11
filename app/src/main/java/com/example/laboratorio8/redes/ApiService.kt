package com.example.laboratorio8.redes

import android.os.Bundle
import androidx.core.text.parseAsHtml
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val BASE_URL="https://api.github.com"
private var valors =""
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit=Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(
    moshi)).baseUrl(
    BASE_URL).build()


interface ApiService {


    @GET("/users/{valors}")
    fun getProperties(@Path("valors") valors: String?):
            Call<GitProperty>
}

object ApiServices{
    val retrofitService:ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
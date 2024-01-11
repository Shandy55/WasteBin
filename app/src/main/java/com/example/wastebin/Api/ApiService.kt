package com.rsz.getapirickmorty.Api

import com.example.wastebin.ResponseMorty
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    fun getMorty() : Call<ResponseMorty>
}
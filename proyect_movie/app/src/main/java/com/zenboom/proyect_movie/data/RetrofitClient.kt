package com.zenboom.proyect_movie.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // Si usas el emulador de Android: 10.0.2.2
    // Si usas celular físico: La IP de tu PC (ej. 192.168.1.XX)
    private const val BASE_URL = "http://10.0.2.2:8080/"

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
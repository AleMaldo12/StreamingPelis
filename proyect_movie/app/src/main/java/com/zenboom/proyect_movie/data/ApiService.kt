package com.zenboom.proyect_movie.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("api/peliculas")
    suspend fun getPeliculas(): Response<List<Pelicula>>
    suspend fun obtenerPeliculas(): Response<List<Pelicula>>

    @POST("api/usuarios/login")
    suspend fun hacerLogin(@Body request: LoginRequest): Response<Void> // Cambia Void si tu API devuelve un token o un objeto
}
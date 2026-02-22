package com.zenboom.proyect_movie.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    // Para MainActivity: debe llamarse 'obtenerPeliculas'
    @GET("api/peliculas")
    suspend fun obtenerPeliculas(): Response<List<Pelicula>>

    // Para LoginActivity: debe llamarse 'hacerLogin'
    @POST("api/usuarios/login")
    suspend fun hacerLogin(@Body request: LoginRequest): Response<Usuario>

    @GET("api/usuarios")
    suspend fun getUsuarios(): Response<List<Usuario>>
}
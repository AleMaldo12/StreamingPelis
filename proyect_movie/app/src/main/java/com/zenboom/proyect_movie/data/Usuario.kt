package com.zenboom.proyect_movie.data

import com.google.gson.annotations.SerializedName

data class Usuario(
    val id: Int,
    val nombre: String,
    val email: String,     // Antes tenías 'correo'
    val password: String,  // Antes tenías 'contrasena'
    val rol: String
)
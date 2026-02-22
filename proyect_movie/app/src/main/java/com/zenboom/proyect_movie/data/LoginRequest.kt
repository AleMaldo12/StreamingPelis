package com.zenboom.proyect_movie.data

data class LoginRequest(
    val email: String,    // Verifica si tu API espera 'usuario' o 'email'
    val password: String  // Verifica si tu API espera 'contrasena' o 'password'
)
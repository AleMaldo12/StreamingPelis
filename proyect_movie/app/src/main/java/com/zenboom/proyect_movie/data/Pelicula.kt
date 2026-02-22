package com.zenboom.proyect_movie.data

data class Pelicula(
    val id: Long,
    val titulo: String,
    val genero: String,
    val descripcion: String,
    val imagenUrl: String
)
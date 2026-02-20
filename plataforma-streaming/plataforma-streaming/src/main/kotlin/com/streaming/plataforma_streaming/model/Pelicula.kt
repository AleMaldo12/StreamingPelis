package com.streaming.plataforma_streaming.model

import jakarta.persistence.*

@Entity
@Table(name = "peliculas")
data class Pelicula(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val titulo: String = "",

    val genero: String,

    val anioLanzamiento: Int,

    @Column(length = 500)
    val sinopsis: String
)
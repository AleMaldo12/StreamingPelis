package com.streaming.plataforma_streaming.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "usuarios")
data class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var nombre: String = "",

    @Column(nullable = false, unique = true)
    var email: String = "",

    @Column(nullable = false)
    var password: String = "", // ¡Nota: En el futuro esto debe ir encriptado!

    val fechaRegistro: LocalDateTime = LocalDateTime.now()
)
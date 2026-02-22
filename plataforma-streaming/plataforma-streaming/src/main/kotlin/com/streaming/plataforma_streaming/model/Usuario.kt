package com.streaming.plataforma_streaming.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "usuarios")
data class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val nombre: String = "",
    val email: String = "",
    val password: String = "",
    val rol: String = "CASUAL" // "ADMIN" o "CASUAL"
)
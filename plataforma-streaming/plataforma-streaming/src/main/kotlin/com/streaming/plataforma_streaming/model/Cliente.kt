package com.streaming.plataforma_streaming.model

import jakarta.persistence.*

@Entity
@Table(name = "clientes")
data class Cliente(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val nombre: String = "",

    @Column(nullable = false, unique = true)
    val email: String = "",

    val plan: String = "Básico",
    val estado: String = "ACTIVO"
)
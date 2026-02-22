package com.streaming.plataforma_streaming

import com.streaming.plataforma_streaming.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Long> {
    // Debe llamarse exactamente así para que Spring encuentre "email" en tu modelo
    fun findByEmail(email: String): Usuario?
}
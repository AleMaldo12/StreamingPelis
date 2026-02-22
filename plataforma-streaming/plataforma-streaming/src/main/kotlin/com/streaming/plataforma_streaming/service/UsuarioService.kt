package com.streaming.plataforma_streaming.service

import com.streaming.plataforma_streaming.UsuarioRepository
import com.streaming.plataforma_streaming.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val repository: UsuarioRepository) {
    fun registrar(usuario: Usuario): Usuario = repository.save(usuario)
    fun listarTodos(): List<Usuario> = repository.findAll()

    // Cambiado de buscarPorCorreo a buscarPorEmail
    fun buscarPorEmail(email: String): Usuario? = repository.findByEmail(email)
}
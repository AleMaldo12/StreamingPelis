package com.streaming.plataforma_streaming

import com.streaming.plataforma_streaming.model.Usuario
import com.streaming.plataforma_streaming.service.UsuarioService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/usuarios")
class UsuarioController(private val service: UsuarioService) {

    @PostMapping("/registro")
    fun registrar(@RequestBody usuario: Usuario): Usuario = service.registrar(usuario)

    @GetMapping
    fun obtenerTodos(): List<Usuario> = service.listarTodos()
}
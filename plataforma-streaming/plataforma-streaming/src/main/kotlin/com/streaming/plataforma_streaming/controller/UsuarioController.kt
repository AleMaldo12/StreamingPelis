package com.streaming.plataforma_streaming.controller

import com.streaming.plataforma_streaming.model.Usuario
import com.streaming.plataforma_streaming.service.UsuarioService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/usuarios")
class UsuarioController(private val service: UsuarioService) {

    @PostMapping("/registrar")
    fun registrar(@RequestBody usuario: Usuario): ResponseEntity<Usuario> {
        // Retornamos ResponseEntity para tener más control sobre los códigos de estado
        return ResponseEntity.ok(service.registrar(usuario))
    }

    @PostMapping("/login")
    fun login(@RequestBody credenciales: Map<String, String>): ResponseEntity<Any> {
        val emailRecibido = credenciales["email"] ?: ""
        val passRecibida = credenciales["password"] ?: ""

        val usuario = service.buscarPorEmail(emailRecibido)

        return if (usuario != null && usuario.password == passRecibida) {
            // AGREGAMOS EL ROL AQUÍ:
            ResponseEntity.ok(mapOf(
                "mensaje" to "Acceso exitoso",
                "nombre" to usuario.nombre,
                "rol" to usuario.rol // <--- Esta es la pieza que faltaba
            ))
        } else {
            ResponseEntity.status(401).body(mapOf("error" to "Credenciales incorrectas"))
        }
    }

    @GetMapping
    fun listarTodos(): List<Usuario> = service.listarTodos() // Asegúrate que el service tenga esta función

    @GetMapping("/listar")
    fun obtenerTodos(): List<Usuario> = service.listarTodos()
}
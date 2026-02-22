package com.streaming.plataforma_streaming.controller

import com.streaming.plataforma_streaming.model.Cliente
import com.streaming.plataforma_streaming.repository.ClienteRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = ["*"])
class ClienteController(private val repository: ClienteRepository) {

    @PostMapping
    fun registrar(@RequestBody cliente: Cliente): Cliente {
        return repository.save(cliente)
    }

    @GetMapping
    fun listarTodos(): List<Cliente> = repository.findAll()
}
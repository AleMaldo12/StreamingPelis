package com.streaming.plataforma_streaming.controller

import com.streaming.plataforma_streaming.service.PeliculaService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class WebController(private val peliculaService: PeliculaService) {

    @GetMapping("/")
    fun paginaInicio(model: Model): String {
        // Obtenemos las películas de AWS para pasarlas a la vista
        val listaPeliculas = peliculaService.listarTodas()
        model.addAttribute("peliculas", listaPeliculas)
        return "index" // Esto busca src/main/resources/templates/index.html
    }

    @GetMapping("/registro")
    fun paginaRegistro(): String {
        return "registro" // busca registro.html
    }
}
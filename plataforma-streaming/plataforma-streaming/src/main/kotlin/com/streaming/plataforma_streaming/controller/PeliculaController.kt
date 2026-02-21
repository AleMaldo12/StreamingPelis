package com.streaming.plataforma_streaming

import com.streaming.plataforma_streaming.model.Pelicula
import com.streaming.plataforma_streaming.service.PeliculaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.data.domain.Page

@CrossOrigin(origins = ["*"]) // Permite que cualquier web consuma tu API
@RestController
@RequestMapping("/api/peliculas")
class PeliculaController(private val service: PeliculaService) {

    @GetMapping
    fun obtenerTodas(
        @RequestParam(required = false) genero: String?,
        @RequestParam(required = false) titulo: String?
    ): List<Pelicula> {
        return when {
            genero != null -> service.buscarPorGenero(genero)
            titulo != null -> service.buscarPorTitulo(titulo)
            else -> service.listarTodas()
        }
    }
    @GetMapping("/paginado")
    fun obtenerPaginadas(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<Page<Pelicula>> {
        val resultado = service.listarPaginado(page, size)
        return ResponseEntity.ok(resultado)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun crear(@RequestBody pelicula: Pelicula): Pelicula = service.guardar(pelicula)

    @PutMapping("/{id}")
    fun actualizar(@PathVariable id: Long, @RequestBody pelicula: Pelicula): ResponseEntity<Pelicula> {
        val actualizada = service.actualizar(id, pelicula)
        return if (actualizada != null) ResponseEntity.ok(actualizada)
        else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<Unit> {
        return if (service.eliminar(id)) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()
    }
}
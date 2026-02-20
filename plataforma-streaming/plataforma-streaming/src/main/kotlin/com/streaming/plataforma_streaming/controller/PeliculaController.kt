package com.streaming.plataforma_streaming

import com.streaming.plataforma_streaming.model.Pelicula
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/peliculas")
class PeliculaController(private val repository: PeliculaRepository) {

    @GetMapping
    fun obtenerTodas(): List<Pelicula> = repository.findAll()

    @PostMapping
    fun crear(@RequestBody pelicula: Pelicula): Pelicula = repository.save(pelicula)

    // Actualizar una película existente: http://localhost:8080/api/peliculas/1
    @PutMapping("/{id}")
    fun actualizar(@PathVariable id: Long, @RequestBody peliculaActualizada: Pelicula): Pelicula {
        val peliculaExistente = repository.findById(id).orElseThrow {
            RuntimeException("No encontrada")
        }

        // Crea una nueva instancia basada en la anterior pero con datos nuevos
        val nuevaPelicula = peliculaExistente.copy(
            id = peliculaExistente.id, // <--- Forzamos que el ID sea el mismo
            titulo = peliculaActualizada.titulo,
            genero = peliculaActualizada.genero,
            anioLanzamiento = peliculaActualizada.anioLanzamiento,
            sinopsis = peliculaActualizada.sinopsis
        )

        return repository.save(nuevaPelicula)
    }

    // Eliminar una película: http://localhost:8080/api/peliculas/1
    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long) {
        repository.deleteById(id)
    }

}
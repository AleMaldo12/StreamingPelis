package com.streaming.plataforma_streaming.service

import com.streaming.plataforma_streaming.PeliculaRepository
import com.streaming.plataforma_streaming.model.Pelicula
import org.springframework.stereotype.Service
import java.time.LocalDate
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

@Service
class PeliculaService(private val repository: PeliculaRepository) {

    fun listarPaginado(pagina: Int, tamaño: Int): Page<Pelicula> {
        val pageable: Pageable = PageRequest.of(pagina, tamaño)
        return repository.findAll(pageable)
    }

    fun buscarPorId(id: Long): Pelicula? = repository.findById(id).orElse(null)

    fun listarTodas(): List<Pelicula> = repository.findAll()

    fun guardar(pelicula: Pelicula): Pelicula {
        validarPelicula(pelicula)
        return repository.save(pelicula)
    }
    fun buscarPorGenero(genero: String): List<Pelicula> {
        return repository.findByGeneroIgnoreCase(genero)
    }
    fun buscarPorTitulo(titulo: String): List<Pelicula> {
        return repository.findByTituloContainingIgnoreCase(titulo)
    }

    fun actualizar(id: Long, datosNuevos: Pelicula): Pelicula? {
        val existente = repository.findById(id).orElse(null) ?: return null

        validarPelicula(datosNuevos)

        val actualizada = existente.copy(
            titulo = datosNuevos.titulo,
            genero = datosNuevos.genero,
            anioLanzamiento = datosNuevos.anioLanzamiento,
            sinopsis = datosNuevos.sinopsis,
            urlImagen = datosNuevos.urlImagen // <--- No olvides este
        )
        return repository.save(actualizada)
    }

    fun eliminar(id: Long): Boolean {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            true
        } else {
            false
        }
    }

    // --- Lógica de validación ---
    private fun validarPelicula(pelicula: Pelicula) {
        val anioActual = LocalDate.now().year

        if (pelicula.titulo.isBlank()) {
            throw IllegalArgumentException("El título de la película no puede estar vacío.")
        }

        if (pelicula.anioLanzamiento > anioActual) {
            throw IllegalArgumentException("El año de lanzamiento ($pelicula.anioLanzamiento) no puede ser mayor al año actual ($anioActual).")
        }

        if (pelicula.anioLanzamiento < 1888) { // Año de la primera película de la historia
            throw IllegalArgumentException("El año de lanzamiento no puede ser anterior a 1888.")
        }
    }
}
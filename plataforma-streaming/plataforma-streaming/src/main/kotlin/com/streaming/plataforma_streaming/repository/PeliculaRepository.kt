package com.streaming.plataforma_streaming

import com.streaming.plataforma_streaming.model.Pelicula
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PeliculaRepository : JpaRepository<Pelicula, Long> {
    fun findByGeneroIgnoreCase(genero: String): List<Pelicula>

    // Buscará cualquier película cuyo título contenga la palabra ignorando mayúsculas
    fun findByTituloContainingIgnoreCase(titulo: String): List<Pelicula>
}
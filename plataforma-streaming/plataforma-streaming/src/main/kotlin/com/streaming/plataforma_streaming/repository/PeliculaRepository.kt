package com.streaming.plataforma_streaming

import com.streaming.plataforma_streaming.model.Pelicula
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PeliculaRepository : JpaRepository<Pelicula, Long>
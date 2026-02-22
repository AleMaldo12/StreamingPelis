package com.streaming.plataforma_streaming.repository

import com.streaming.plataforma_streaming.model.Cliente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClienteRepository : JpaRepository<Cliente, Long>
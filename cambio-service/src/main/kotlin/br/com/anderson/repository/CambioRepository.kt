package br.com.anderson.repository

import br.com.anderson.model.Cambio
import org.springframework.data.jpa.repository.JpaRepository

interface CambioRepository: JpaRepository<Cambio, Long?> {
    fun findByFromAndTo(from: String, to: String): Cambio?
}
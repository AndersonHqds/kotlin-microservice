package br.com.anderson.repository

import br.com.anderson.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository: JpaRepository<Book, Long?>
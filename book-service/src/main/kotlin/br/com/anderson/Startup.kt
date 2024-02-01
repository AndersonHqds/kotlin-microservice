package br.com.anderson

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class BookServiceApplication

fun main(args: Array<String>) {
	runApplication<BookServiceApplication>(*args)
}

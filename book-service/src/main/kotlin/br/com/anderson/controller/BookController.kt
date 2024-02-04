package br.com.anderson.controller

import br.com.anderson.model.Book
import br.com.anderson.proxy.CambioProxy
import br.com.anderson.repository.BookRepository
import br.com.anderson.response.Cambio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.util.*

@RestController
@RequestMapping("book-service")
class BookController {
    @Autowired
    private lateinit var environment: Environment
    @Autowired
    private lateinit var repository: BookRepository
    @Autowired
    private lateinit var proxy: CambioProxy
    @GetMapping(value = ["/{id}/{currency}"])
    fun findBook(
            @PathVariable("id") id: Long,
            @PathVariable("currency") currency: String): Book? {
        val book = repository.findById(id).orElseThrow{ RuntimeException("Book Not Found")}
        val cambio = proxy.getCambio(book.price, "USD", currency)
        val port = environment.getProperty("local.server.port")
        book.environment = "BOOK PORT: $port CAMBIO PORT: ${cambio!!.environment}"
        book.currency = currency
        book.price = cambio!!.convertedValue
        return book
    }

    @GetMapping(value = ["/v1/{id}/{currency}"])
    fun findBookV1(
            @PathVariable("id") id: Long,
            @PathVariable("currency") currency: String): Book? {
        val book = repository.findById(id).orElseThrow{ RuntimeException("Book Not Found")}
        val params = HashMap<String, String>()
        params["amount"] = book.price.toString()
        params["from"] = "USD"
        params["to"] = currency
        val response = RestTemplate()
                .getForEntity(
                        "http://localhost:8000/cambio-service/{amount}/{from}/{to}",
                        Cambio::class.java,
                        params
                )
        val cambio = response.body
        val port = environment.getProperty("local.server.port")
        book.environment = port
        book.currency = currency
        book.price = cambio!!.convertedValue
        return book
    }
}
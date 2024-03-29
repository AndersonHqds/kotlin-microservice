package br.com.anderson.proxy

import br.com.anderson.response.Cambio
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "cambio-service")
interface CambioProxy {
    @GetMapping(value = ["/cambio-service/{amount}/{from}/{to}"])
    fun getCambio(
            @PathVariable("amount") amount: Double?,
            @PathVariable("from") from: String,
            @PathVariable("to") to: String,
    ): Cambio?
}
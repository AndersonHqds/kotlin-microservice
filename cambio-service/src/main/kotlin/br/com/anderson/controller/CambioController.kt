package br.com.anderson.controller

import br.com.anderson.model.Cambio
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("cambio-service")
class CambioController {
    @GetMapping(value = ["/{amount}/{from}/{to}"])
    fun getCambio(
            @PathVariable("amount") amount: BigDecimal,
            @PathVariable("from") from: String,
            @PathVariable("to") to: String,
            ): Cambio {
        return Cambio(
                id = 1L,
                from = from,
                to = to,
                conversionFactor = BigDecimal.ONE,
                convertedValue = BigDecimal.ONE,
                environment = "PORT 8000"
        )
    }
}
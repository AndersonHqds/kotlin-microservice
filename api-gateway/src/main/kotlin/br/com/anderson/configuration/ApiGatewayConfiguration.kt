package br.com.anderson.configuration

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiGatewayConfiguration {
    @Bean
    fun gatewayRouter(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
                .route{
                    p: PredicateSpec ->
                    p.path("/cambio-service/**").uri("lb://cambio-service/")
                }.route{
                    p: PredicateSpec ->
                    p.path("/book-service/**").uri("lb://book-service/")
                }
                .build()
    }
}
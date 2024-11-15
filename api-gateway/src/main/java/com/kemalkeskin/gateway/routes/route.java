package com.kemalkeskin.gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicate;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

@Configuration
public class route {


    @Bean
    public RouterFunction<ServerResponse> getProductService() {
        return GatewayRouterFunctions.route("product_service")
                .route(RequestPredicates.path("/products"), HandlerFunctions.http("http://localhost:8080")).build();


    }

   /* @Bean
    public RouterFunction<ServerResponse> getProductServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("product_service_swagger")
                .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8080")).filter(setPath("/api-docs")).build();
    }*/

    @Bean
    public RouterFunction<ServerResponse> getOrderService() {
        return GatewayRouterFunctions.route("order_service")
                .route(RequestPredicates.path("/orders"), HandlerFunctions.http("http://localhost:8081")).build();

    }
  /*  @Bean
    public RouterFunction<ServerResponse> getOrderServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("order_service_swagger")
                .route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8081")).filter(setPath("/api-docs")).build();
    }*/



}

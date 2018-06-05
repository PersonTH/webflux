package com.imooc.firstdemo.config;

import com.imooc.firstdemo.domain.User;
import com.imooc.firstdemo.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * 路由器配置
 */
@Configuration
public class RouterFunctionConfiguration {

    @Bean
    @Autowired
    public RouterFunction<ServerResponse> personFindAll(UserRespository userRespository) {
     return RouterFunctions.route(RequestPredicates.GET("/person/find/all"),
                request -> {
                    Collection<User> users = userRespository.findAll();
                    Flux<User> userFlux = Flux.fromIterable(users);
                    Mono<ServerResponse> body = ServerResponse.ok().body(userFlux, User.class);
                    return body;
                });
    }

}

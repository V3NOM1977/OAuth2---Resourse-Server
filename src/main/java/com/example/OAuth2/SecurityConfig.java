package com.example.OAuth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // NON OPAQUE TOKENS
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {
    // http.oauth2ResourceServer(
    // r -> r.jwt(jwt -> jwt
    // .jwkSetUri("http://localhost:8080/oauth2/jwks")
    // .jwtAuthenticationConverter(new CustomJwtAuthenticationTokenConverter()))

    // );

    // http.authorizeHttpRequests(auth -> {
    // auth.anyRequest().authenticated();
    // });
    // return http.build();
    // }

    // OPAQUE TOKENS
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(oauth2 -> oauth2
                .opaqueToken(opaqueToken -> opaqueToken
                        .introspectionUri("http://localhost:8080/oauth2/introspect")
                        .introspectionClientCredentials("client", "secret")));

        http.authorizeHttpRequests(auth -> {
            auth.anyRequest().authenticated();
        });
        return http.build();
    }
}
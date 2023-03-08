package com.krokodillLl.traveldiarygateway.configuration;

import com.krokodillLl.traveldiarygateway.configuration.util.CustomJwtConverter;
import com.krokodillLl.traveldiarygateway.filter.MatchPathToRoleFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MatchPathToRoleFilter matchPathToRoleFilter;
    private final CustomJwtConverter customJwtConverter;

    @Bean
    public SecurityWebFilterChain configureResourceServer(ServerHttpSecurity httpSecurity) {

        return httpSecurity
                .authorizeExchange()
                .pathMatchers("/actuator/health/**", "/*/*/*/public/**").permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .addFilterAfter(matchPathToRoleFilter, SecurityWebFiltersOrder.AUTHORIZATION)
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .logout().disable()
                .oauth2ResourceServer().jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                .and()
                .build();
    }

    @Bean
    public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
        var jwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
                new ReactiveJwtGrantedAuthoritiesConverterAdapter(customJwtConverter));

        return jwtAuthenticationConverter;
    }
}

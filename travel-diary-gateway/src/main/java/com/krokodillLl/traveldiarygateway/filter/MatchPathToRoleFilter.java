package com.krokodillLl.traveldiarygateway.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MatchPathToRoleFilter implements WebFilter {

    @Value("${security.access.path.moderator}")
    private String moderatorPath;
    @Value("${security.access.role.name.moderator}")
    private String moderatorName;
    @Value("${security.access.path.admin}")
    private String adminPath;
    @Value("${security.access.role.name.admin}")
    private String adminName;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return exchange.getPrincipal()
                .flatMap(principal -> checkPathForRoleAccess(exchange, chain, principal))
                .switchIfEmpty(chain.filter(exchange))
                .onErrorStop();
    }

    Mono<Void> checkPathForRoleAccess(ServerWebExchange exchange, WebFilterChain chain, Principal principal) {
        var paths = extractPathsFromRequest(exchange.getRequest().getPath().value());
        var requestRoles = extractRoles(principal);

        if (
                hasProblemForRole(paths, requestRoles, moderatorPath, moderatorName) ||
                        hasProblemForRole(paths, requestRoles, adminPath, adminName)
        ) {
            return Mono.error(() -> new AccessDeniedException("Request rejected: access denied"));
        }

        return chain.filter(exchange);
    }

    private Set<String> extractPathsFromRequest(String fullPath) {
        return Arrays.stream(fullPath.split("/"))
                .collect(Collectors.toSet());
    }

    private Set<String> extractRoles(Principal principal) {
        return ((JwtAuthenticationToken) principal).getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .map(roleName -> roleName.substring(5))
                .collect(Collectors.toSet());
    }

    private boolean hasProblemForRole(Set<String> pathsSet, Set<String> requestRoles, String rolePath, String roleName) {
        return pathsSet.contains(rolePath) && !requestRoles.contains(roleName);
    }

}

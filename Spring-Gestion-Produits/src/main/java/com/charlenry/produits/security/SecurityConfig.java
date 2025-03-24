package com.charlenry.produits.security;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Cette une classe utilisée pour configurer Spring Security dans l'application.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)  // Permet d'ajouter des filtres directement dans les restControllers ou au niveau des méthodes de service.
public class SecurityConfig {

    /** 
	 * Cette méthode est utilisée pour configurer la chaîne de filtres de sécurité de Spring Security.
	 * Une chaîne de filtres est une liste ordonnée de filtres qui sont appliqués à chaque requête entrante. 
	 * Chaque filtre dans la chaîne peut décider de traiter la requête et de générer une réponse, 
	 * ou de passer la requête au filtre suivant dans la chaîne.
	 * 
	 * @param http - Objet de la classe HttpSecurity fournie par Spring Security et est utilisé pour configurer les détails de sécurité au niveau des requêtes HTTP.
	 * @return SecurityFilterChain - C'est une interface fournie par Spring Security qui représente une chaîne de filtres de sécurité.
	 * @throws Exception
	 */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .csrf(csrf -> csrf.disable())

        .cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
          @Override
          public CorsConfiguration getCorsConfiguration(@SuppressWarnings("null") HttpServletRequest request) {
            CorsConfiguration cors = new CorsConfiguration();
            cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
            cors.setAllowedMethods(Collections.singletonList("*"));
            cors.setAllowedHeaders(Collections.singletonList("*"));
            cors.setExposedHeaders(Collections.singletonList("Authorization"));
            return cors;
          }
        }))
        .authorizeHttpRequests(requests -> requests
        	//.anyRequest().permitAll());
            .requestMatchers(HttpMethod.GET, "/api/all").hasAnyAuthority("ADMIN", "USER")
            .requestMatchers(HttpMethod.GET, "/api/getProdById/**").hasAnyAuthority("ADMIN", "USER")
            .requestMatchers(HttpMethod.POST, "/api/addProd").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/api/updateProd").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/delProdById/**").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.GET, "/api/prodsByCat/**").hasAnyAuthority("ADMIN", "USER")
            .requestMatchers(HttpMethod.GET, "/api/prodsByName/**").hasAnyAuthority("ADMIN", "USER")
            .requestMatchers(HttpMethod.DELETE, "/api/cat/delCatById/**").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.GET, "/api/image/getInfo/**").hasAnyAuthority("ADMIN", "USER")
            .requestMatchers(HttpMethod.GET, "/api/image/loadFromFS/**").hasAnyAuthority("ADMIN", "USER")
            .requestMatchers(HttpMethod.POST, "/api/image/upload").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.POST, "/api/image/uploadImageProd/**").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.POST, "/api/image/uploadFS/**").hasAuthority("ADMIN")   
            .requestMatchers(HttpMethod.DELETE, "/api/image/delete/**").hasAuthority("ADMIN")
            .anyRequest().authenticated())
        .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

}

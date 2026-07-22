package com.mohit.ecommerce.config;
import com.nimbusds.jose.jwk.source.ImmutableSecret; import com.mohit.ecommerce.security.CustomUserDetailsService; import jakarta.crypto.SecretKey; import jakarta.crypto.spec.SecretKeySpec; import org.springframework.beans.factory.annotation.Value; import org.springframework.context.annotation.*; import org.springframework.security.authentication.*; import org.springframework.security.authentication.dao.DaoAuthenticationProvider; import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration; import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.config.http.SessionCreationPolicy; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.security.oauth2.jose.jws.MacAlgorithm; import org.springframework.security.oauth2.jwt.*; import org.springframework.security.oauth2.server.resource.authentication.*; import org.springframework.security.web.SecurityFilterChain; import org.springframework.web.cors.*; import java.nio.charset.StandardCharsets; import java.util.List;
@Configuration @EnableMethodSecurity
public class SecurityConfig{
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean AuthenticationProvider authenticationProvider(CustomUserDetailsService uds,PasswordEncoder pe){DaoAuthenticationProvider p=new DaoAuthenticationProvider(uds);p.setPasswordEncoder(pe);return p;}
 @Bean AuthenticationManager authenticationManager(AuthenticationConfiguration c)throws Exception{return c.getAuthenticationManager();}
 @Bean JwtEncoder jwtEncoder(@Value("${app.jwt.secret}") String secret){SecretKey key=new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),"HmacSHA256");return new NimbusJwtEncoder(new ImmutableSecret<>(key));}
 @Bean JwtDecoder jwtDecoder(@Value("${app.jwt.secret}") String secret){SecretKey key=new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),"HmacSHA256");return NimbusJwtDecoder.withSecretKey(key).macAlgorithm(MacAlgorithm.HS256).build();}
 @Bean JwtAuthenticationConverter jwtAuthenticationConverter(){JwtGrantedAuthoritiesConverter g=new JwtGrantedAuthoritiesConverter();g.setAuthoritiesClaimName("roles");g.setAuthorityPrefix("ROLE_");JwtAuthenticationConverter c=new JwtAuthenticationConverter();c.setJwtGrantedAuthoritiesConverter(g);return c;}
 @Bean SecurityFilterChain securityFilterChain(HttpSecurity http,JwtAuthenticationConverter converter)throws Exception{return http.csrf(c->c.disable()).cors(c->c.configurationSource(corsConfigurationSource())).sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(a->a
  .requestMatchers("/api/auth/**").permitAll()
  .requestMatchers(org.springframework.http.HttpMethod.GET,"/api/products/**","/api/categories/**").permitAll()
  .requestMatchers("/api/admin/**").hasRole("ADMIN")
  .requestMatchers("/api/cart/**","/api/orders/**","/api/users/me").authenticated()
  .anyRequest().authenticated())
  .oauth2ResourceServer(o->o.jwt(j->j.jwtAuthenticationConverter(converter))).build();}
 @Bean CorsConfigurationSource corsConfigurationSource(){CorsConfiguration c=new CorsConfiguration();c.setAllowedOrigins(List.of("http://localhost:3000","http://localhost:5173"));c.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));c.setAllowedHeaders(List.of("*"));c.setAllowCredentials(true);UrlBasedCorsConfigurationSource s=new UrlBasedCorsConfigurationSource();s.registerCorsConfiguration("/**",c);return s;}
}

package com.mohit.ecommerce.config;
import com.mohit.ecommerce.entity.AppUser; import com.mohit.ecommerce.enums.Role; import com.mohit.ecommerce.repository.UserRepository; import org.springframework.beans.factory.annotation.Value; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.*; import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class DataInitializer{
 @Bean CommandLineRunner seedAdmin(UserRepository users,PasswordEncoder encoder,@Value("${app.admin.name}") String name,@Value("${app.admin.email}") String email,@Value("${app.admin.password}") String password){return args->{if(!users.existsByEmailIgnoreCase(email)){AppUser u=new AppUser();u.setName(name);u.setEmail(email.toLowerCase());u.setPassword(encoder.encode(password));u.setRole(Role.ADMIN);users.save(u);}};}
}

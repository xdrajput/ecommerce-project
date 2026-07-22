package com.mohit.ecommerce.security;
import com.mohit.ecommerce.entity.AppUser; import com.mohit.ecommerce.repository.UserRepository; import org.springframework.security.core.userdetails.*; import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailsService implements UserDetailsService{
 private final UserRepository users; public CustomUserDetailsService(UserRepository users){this.users=users;}
 public UserDetails loadUserByUsername(String email){AppUser u=users.findByEmailIgnoreCase(email).orElseThrow(()->new UsernameNotFoundException("User not found")); return User.withUsername(u.getEmail()).password(u.getPassword()).roles(u.getRole().name()).build();}
}

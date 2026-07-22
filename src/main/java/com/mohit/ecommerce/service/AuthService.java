package com.mohit.ecommerce.service;
import com.mohit.ecommerce.dto.*; import com.mohit.ecommerce.entity.AppUser; import com.mohit.ecommerce.enums.Role; import com.mohit.ecommerce.exception.BusinessException; import com.mohit.ecommerce.repository.UserRepository; import com.mohit.ecommerce.security.JwtService; import org.springframework.security.authentication.*; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service;
@Service
public class AuthService{
 private final UserRepository users; private final PasswordEncoder encoder; private final AuthenticationManager auth; private final JwtService jwt;
 public AuthService(UserRepository u,PasswordEncoder e,AuthenticationManager a,JwtService j){users=u;encoder=e;auth=a;jwt=j;}
 public UserResponse register(RegisterRequest r){if(users.existsByEmailIgnoreCase(r.email()))throw new BusinessException("Email already registered");AppUser u=new AppUser();u.setName(r.name());u.setEmail(r.email().toLowerCase());u.setPassword(encoder.encode(r.password()));u.setRole(Role.CUSTOMER);u=users.save(u);return new UserResponse(u.getId(),u.getName(),u.getEmail(),u.getRole().name());}
 public AuthResponse login(LoginRequest r){Authentication a=auth.authenticate(new UsernamePasswordAuthenticationToken(r.email(),r.password()));String token=jwt.generate(a);String role=a.getAuthorities().iterator().next().getAuthority().replace("ROLE_","");return new AuthResponse(token,"Bearer",a.getName(),role);}
}

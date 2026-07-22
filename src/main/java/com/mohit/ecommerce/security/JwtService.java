package com.mohit.ecommerce.security;
import org.springframework.beans.factory.annotation.Value; import org.springframework.security.core.Authentication; import org.springframework.security.oauth2.jose.jws.MacAlgorithm; import org.springframework.security.oauth2.jwt.*; import org.springframework.stereotype.Service; import java.time.*; import java.util.List;
@Service
public class JwtService{
 private final JwtEncoder encoder; private final long minutes;
 public JwtService(JwtEncoder encoder,@Value("${app.jwt.expiration-minutes}") long minutes){this.encoder=encoder;this.minutes=minutes;}
 public String generate(Authentication authentication){Instant now=Instant.now(); List<String> roles=authentication.getAuthorities().stream().map(a->a.getAuthority().replace("ROLE_","")).toList(); JwtClaimsSet claims=JwtClaimsSet.builder().issuer("ecommerce-api").issuedAt(now).expiresAt(now.plus(Duration.ofMinutes(minutes))).subject(authentication.getName()).claim("roles",roles).build(); JwsHeader header=JwsHeader.with(MacAlgorithm.HS256).build(); return encoder.encode(JwtEncoderParameters.from(header,claims)).getTokenValue();}
}

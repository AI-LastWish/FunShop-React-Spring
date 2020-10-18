package funShop.security;

import org.springframework.stereotype.Component;
import funShop.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.Authentication;

import static funShop.security.SecurityConstants.EXPIRATION_TIME;
import static funShop.security.SecurityConstants.SECRET;

@Component
public class JwtTokenProvider {

	// Generate the token
	public String generateToken(Authentication authentication) {
		
		User user = (User) authentication.getPrincipal();
		Date now = new Date(System.currentTimeMillis());

		Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

		String userId = Long.toString(user.getId());

		Map<String, Object> claims = new HashMap<>();
		claims.put("id", (userId));
		claims.put("username", user.getUsername());
		claims.put("fullName", user.getFullName());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims) // individual infor about the user
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
	}
}

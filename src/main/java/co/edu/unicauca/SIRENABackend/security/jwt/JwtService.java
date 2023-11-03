package co.edu.unicauca.SIRENABackend.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import co.edu.unicauca.SIRENABackend.security.common.enums.TokenTypeEnum;
import co.edu.unicauca.SIRENABackend.security.models.TokenModel;
import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import co.edu.unicauca.SIRENABackend.security.repositories.ITokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Autowired
    private ITokenRepository tokenRepository;

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.expitation}")
    private long jwtExpiration;
    @Value("${jwt.refresh-token.expitation}")
    private long refreshExpiration;

    public String getToken(UserDetails user, Integer prmUserID) {
        return getToken(new HashMap<>(), user, prmUserID);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails userDetails, Integer prmUserID) {
        return buildToken(extraClaims, userDetails, prmUserID, jwtExpiration);
    }

    public String getRefreshToken(UserDetails user, Integer prmUserID) {
        return buildToken(new HashMap<>(), user, prmUserID, refreshExpiration);
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, Integer prmUserID,
            long prmExpiration) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .claim("subId", prmUserID)
                .claim("role", userDetails.getAuthorities().stream().findFirst().get().getAuthority())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + prmExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    public void saveUserToken(UserModel prmUser, String prmJwtToken) {
        var token = TokenModel.builder()
                .user(prmUser)
                .token(prmJwtToken)
                .tokenType(TokenTypeEnum.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public void revokeAllUserTokens(UserModel prmUser) {
        var validUserToken = tokenRepository.findAllValidTokensByUser(prmUser.getId());
        if (validUserToken.isEmpty())
            return;
        validUserToken.forEach(Token -> {
            Token.setExpired(true);
            Token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserToken);
    }

    public Optional<TokenModel> findByToken(String refreshToken) {
        return tokenRepository.findByToken(refreshToken);
    }
}

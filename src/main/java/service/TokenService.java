package service;


@Service
public class TokenService {
    public final JwtEncoder encoder;

    public TokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        String scope = authentication
                                .getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.joining(" "));
                                JwtClaimsSet claims = JwtClaimsSet.builder()
                                .issuer("self")
                                .issuedAt(now)
                                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                                .subject(authentication.getName())
                                .claim("scope", scope)
                                .build();
        return
        this.encoder.encode(JwtEncoderParameters.from(claims)).getTo
        kenValue();
    }

}

package practicum6.primeservices.security;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity

private RSAKey rsaKey;

public SecurityConfig() {
    this.rsaKey = Jwks.generateRsa();
}
public class SecurityConfig {
}
@Bean
public JWKSource<SecurityContext> jwkSource() {
    JWKSet jwkSet = new JWKSet(rsaKey);
    return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
}
@Bean
JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwks) {
    return new NimbusJwtEncoder(jwks);
}
@Bean
JwtDecoder jwtDecoder() throws JOSEException {
    return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
}

@Bean
public AuthenticationManager authManager(UserDetailsService userDetailsService) {
    var authprovider = new DaoAuthenticationProvider();
    authprovider.setUserDetailsService(userDetailsService);
    authprovider.setPasswordEncoder(new BCryptPasswordEncoder());
    return new ProviderManager(authprovider);
}
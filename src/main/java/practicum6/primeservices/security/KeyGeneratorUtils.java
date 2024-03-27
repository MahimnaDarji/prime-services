package practicum6.primeservices.security;
import com.nimbusds.jose.jwk.RSAKey;

@Component

public class KeyGeneratorUtils {

    private KeyGeneratorUtils() {}

    static KeyPair generateRsaKey() {

        KeyPair keyPair;

        try {

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();

        } catch (Exception ex) {

            throw new IllegalStateException(ex);

        }

        return keyPair;
    }
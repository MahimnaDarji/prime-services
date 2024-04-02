import java.io.IOException;
@Service
public class AuthenticationService implements IAuthenticationService, UserDetailsService {
    IAuthenticationRepository authenticationRepository;

    AuthenticationDBRepository authenticationRepository;

    public AuthenticationService(
        AuthenticationDBRepository authenticationRepository){
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public Customer register(Customer customer) throws IOException {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String passwordEncoded = bc.encode(customer.getPassword());
        customer.setPassword(passwordEncoded);
        return authenticationRepository.save(customer);
    }
}


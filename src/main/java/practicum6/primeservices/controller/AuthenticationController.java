@RestController
public class AuthenticationController{

    private final IAuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    private TokenService tokenService;

    public AuthenticationController(IAuthenticationService authenticationService, AuthenticationManager authenticationManager, TokenService tokenService){
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public boolean register(@RequestBody Customer customer) {
        try {
            return authenticationService.register(customer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Customer customer) {
        Authentication authentication = authentication.authenticate(
            new UsernamePasswordAuthenticationToken(customer.getUsername(), customer.getPassword())
        );
        return tokenService.generateToken(authentication);
    }
}
package practicum6.primeservices.controller;

@RestController
@CrossOrigin
public class HomeController {
    @GetMapping
    public String greetings(){
        return "Welcome to the Primes Service!";
    }
}

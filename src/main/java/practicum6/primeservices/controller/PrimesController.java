package practicum6.primeservices.controller;

@RestController
@CrossOrigin
@RequestMapping("/primes")
public class PrimesController {
    IPrimesService primesService;

    public PrimesController(IPrimesService primesService) {
        this.primesService = primesService;
    }


    @GetMapping("/{n}")
    public boolean isPrime(@PathVariable int n) {
        return primesService.isPrime(n);
    }
}
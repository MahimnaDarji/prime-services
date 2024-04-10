package practicum6.primeservices.controller;

import practicum6.primeservices.rabbitmq.MQSender;

@RestController
@CrossOrigin
@RequestMapping("/primes")
public class PrimesController {
    IPrimesService primesService;
    private final MQSender mqSender:

    public PrimesController(IPrimesService primesService,MQSender mqSender)
    {
        this.primesService = primesService;
        this.mqSender= mqSender;
    }


    @GetMapping("/{n}")
    public boolean isPrime(@PathVariable int n) {
        boolean result = primesService.isPrime(n);
        Object principal = SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String username =((Jwt) principal).getSubject();
        System.out.println(username);
        mySender.sendMessage(username,n,result);
        return result;
    }
}
package practicum6.primeservices;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PrimesServiceTest {
	PrimesService primesService = new PrimesService();

	@Test
	void _45IsNotPrime() {
		int n = 45;
		boolean expected = false;
		boolean actual = primesService.isPrime(n);
		assertEquals(expected, actual);
	}

	@Test
	void _539828945930573IsNotPrime() {
		Long n = 539828945930573L;
		boolean expected = false;
		boolean actual = primesService.isPrime(n);
		assertEquals(expected, actual);
	}


	@Test
	void _285191IsPrime() {
		long n = 285191;
		boolean expected = true;
		boolean actual = primesService.isPrime(n);
		assertEquals (expected, actual);
	}
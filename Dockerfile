FROM eclipse-termurin:17
WORKDIR /home
COPY ./target/primes-services-0.0.1-SNAPSHOT.jar primes-service.jar
ENTRYPOINT ["java", "-jar", "primes-service.jar"]

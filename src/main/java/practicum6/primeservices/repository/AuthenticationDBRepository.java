package practicum6.primeservices.repository;

@Repository
public interface AuthenticationDBRepository extends CrudRepository<Customer, String> 
{
    Customer findByUsername(String username); 
}


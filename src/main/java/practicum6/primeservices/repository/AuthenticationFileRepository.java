package practicum6.primeservices.repository;
@Repository
public class AuthenticationFileRepository
    implements IAuthenticationRepository, UserDetailsService {
        private static final Logger LOG = LoggerFactory.getLogger(AuthenticationFileRepository.class);
        private static final String DATABASE_NAME = "data/customers.txt";
        private static final String NEW_LINE = System.lineSeparator();

        public AuthenticationFileRepository() {
            File file = new File(DATABASE_NAME);
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                LOG.error(e.getMessage());
            }
        }

        @Override
        public Customer findByUsername(String username) {
            Path path = Paths.get(DATABASE_NAME);
            List<String> data = Files.readAllLines(path);
            for (String line : data){
                if(!line.trim().isEmpty()){
                    String[] properties = line.split(",");
                    if(properties[0].trim().equalsIgnoreCase(username.trim())){
                        return new Customer(properties[0].trim(), properties[1].trim());
                    }

                }
            }
            return null;
        }

        @Override
        public boolean save(Customer customer) throws IOException {
            Customer X = findByUsername(customer.getUsername());
            if(X == null){
                Path path = Paths.get(DATABASE_NAME);
                String data = String.format("%s,%s", customer.getUsername().trim(), customer.getPassword().trim());
                data += NEW_LINE;
                Files.write(path, data.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                return true;
            }
            return false;
        }

        @Override
        public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
                try {
                    Customer customer = authenticationRepository.findByUsername(username);
                    if(customer == null) {
                        throw new UsernameNotFoundException("");
                    }
                    return User
                            .withUsername(username)
                            .password(customer.getPassword())
                            .build();
                } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
}

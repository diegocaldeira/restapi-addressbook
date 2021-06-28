package br.com.addressbook.application;

import br.com.addressbook.domain.Address;
import br.com.addressbook.repository.AddressRepository;
import java.util.Optional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Easily bootstrap and launch a Spring application from a Java main method.
 * 
 * @author Diego Caldeira
 * @version 1.0 - 26 Jun 2021
 */
@SpringBootApplication
@ComponentScan("br.com.addressbook")
@EntityScan("br.com.addressbook.domain")
@EnableJpaRepositories("br.com.addressbook.repository")
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
    
    /**
     * If there's no one record in address table so
     * creates five entities.
     * 
     * @param addressRepository
     * @return 
     */
    @Bean
    CommandLineRunner init(AddressRepository addressRepository) {
        return args -> {
            int [] indexes = { 1, 2, 3, 4, 5 }; 
            for (Integer index : indexes) {
                Optional<Address> domain = addressRepository.findById(index.longValue());
                if (domain.isEmpty()) {
                    addressRepository.save(this.mockAddress(index + "_Address_"));
                }
            }
        };
    }
    
    /**
     * Generate a new pre-filled mock address.
     *
     * @param prefix
     * @return
     */
    private Address mockAddress(String prefix) {
        Address mockAddress = new Address();
        
        mockAddress.setStreetName(prefix + "Street_Name");
        mockAddress.setNumber(prefix + "Number");
        mockAddress.setNeighbourhood(prefix + "Neighbourhood");
        mockAddress.setZipcode(9009009);
        mockAddress.setCity(prefix + "City");
        mockAddress.setState(prefix + "State");
        mockAddress.setCountry(prefix + "Country");
        mockAddress.setLatitude(null);
        mockAddress.setLongitude(null);

        return mockAddress;
    }

}

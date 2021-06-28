package br.com.addressbook.testsuite;

import br.com.addressbook.application.Application;
import br.com.addressbook.domain.Address;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Basic testing for crud operations of the
 * address api endpoint.
 * 
 * Testing Spring Boot Restful Client with RestTemplate.
 * 
 * @author Diego Caldeira
 * @version 1.0 - 26 Jun 2021
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AddressControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;
    
    private final Long id = 3L;

    private String getApiEndpointHost() {
        return "http://localhost:" + port + "/v1/addresses/";
    }

    private String getEndpointWithId() {
        return this.getApiEndpointHost() + id;
    }

    /**
     * Get all addresses registered.
     */
    @Test
    public void testGetAllAddresses() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getApiEndpointHost(), HttpMethod.GET, entity, String.class);

        Assert.assertNotNull(response.getBody());
    }

    /**
     * Find an address record by identification number.
     * 
     */
    @Test
    public void testGetAddressById() {
        Address mockedAddress = restTemplate.getForObject(this.getEndpointWithId(), Address.class);
        Assert.assertNotNull(mockedAddress);
    }

    /**
     * Edit any information from an existing record and
     * verify if it informations was updated.
     * 
     */
    @Test
    public void testUpdateAddress() {
        Address existingAddress = restTemplate.getForObject(this.getEndpointWithId(), Address.class);
        
        Assert.assertNotNull(existingAddress);
        existingAddress.setCity("Street or Road Name");
        restTemplate.put(this.getEndpointWithId(), existingAddress);

        Address updatedAddress = restTemplate.getForObject(this.getEndpointWithId(), Address.class);
        Assert.assertNotNull(updatedAddress);
        Assert.assertEquals(existingAddress.getCity(), updatedAddress.getCity());
    }

    /**
     * Remove an address record if it already exists and
     * verifies if it address has been removed.
     * 
     */
    @Test
    public void testDeleteAddress() {
        final String endpointWithId = this.getApiEndpointHost() + 4L;
        Address existingAddress = restTemplate.getForObject(endpointWithId, Address.class);
        Assert.assertNotNull(existingAddress);
        restTemplate.delete(endpointWithId);

        try {
            existingAddress = restTemplate.getForObject(endpointWithId, Address.class);
            Assert.assertNull(existingAddress);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Generate a new pre-filled mock address.
     *
     * @param prefix
     * @return
     */
    private Address generateMockAddress(String prefix) {
        Address mockAddress = new Address();

        mockAddress.setStreetName(prefix + "_StreetName");
        mockAddress.setNumber(prefix + "_number");
        mockAddress.setNeighbourhood(prefix + "_Neighbourhood");
        mockAddress.setZipcode(9009009);
        mockAddress.setCity(prefix + "_City");
        mockAddress.setState(prefix + "_State");
        mockAddress.setCountry(prefix + "_Country");
        mockAddress.setLatitude(null);
        mockAddress.setLongitude(null);

        return mockAddress;
    }

}






























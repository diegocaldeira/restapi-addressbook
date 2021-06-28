package br.com.addressbook.service;

import br.com.addressbook.domain.Address;
import java.util.Set;
import org.springframework.http.ResponseEntity;

/**
 * Contract for an address service, specifies a behaviour that implementations must have.
 * 
 * @author Diego Caldeira
 * @version 1.0 - 26 Junho 2021
 *
 */
public interface AddressService {
	
    Set<Address> findAll();
    ResponseEntity<Address> findById(Long id);
    Address insert (Address model);
    ResponseEntity<Address> update (Long id, Address model);
    ResponseEntity<?> delete(Long id);

}


















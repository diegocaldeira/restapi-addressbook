package br.com.addressbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.addressbook.domain.Address;
import org.springframework.stereotype.Repository;

/**
 * Contains basic CRUD operations for the address repository.
 * 
 * @author Diego Caldeira
 * @version 1.0 - 25 Jun 2021
 *
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	
}






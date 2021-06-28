package br.com.addressbook.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.addressbook.domain.Address;
import br.com.addressbook.integrations.googlemaps.GoogleGeocodingClient;
import br.com.addressbook.repository.AddressRepository;
import br.com.addressbook.service.AddressService;
import com.google.maps.model.LatLng;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementations of the contract operations of the address service.
 *
 * @author Diego Caldeira
 * @version 1.0 - 26 Jun 2021
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private GoogleGeocodingClient geocodingClient;

    /**
     * Get all addreses from database.
     *
     * @return addresses
     * @see Address
     */
    @Transactional(readOnly = true)
    @Override
    public Set<Address> findAll() {
        log.debug("method findAll");
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    /**
     * Get an address record by identification number.
     *
     * @param id
     * @return an address or not found status
     */
    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<Address> findById(Long id) {
        log.debug("method findById");
        return repository.findById(id)
                .map(record -> {
                    return ResponseEntity.ok().body(record);
                }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new address in repository.
     *
     * @param model
     * @return
     */
    @Override
    public Address insert(Address model) {
        log.debug("method insert");
        this.checkGeocoding(model);
        return repository.save(model);
    }

    /**
     * Update any informations from an existing address.
     *
     * @param id
     * @param model
     * @return
     */
    @Override
    public ResponseEntity<Address> update(Long id, Address model) {
        log.debug("method update");
        return repository.findById(id)
                .map(record -> {
                    record.setStreetName(model.getStreetName());
                    record.setNumber(model.getNumber());
                    record.setComplement(model.getComplement());
                    record.setNeighbourhood(model.getNeighbourhood());
                    record.setZipcode(model.getZipcode());
                    record.setCity(model.getCity());
                    record.setState(model.getState());
                    record.setCountry(model.getCountry());
                    record.setLatitude(model.getLatitude());
                    record.setLongitude(model.getLongitude());

                    Address updated = repository.save(record);

                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Remove an address record if it already exists
     *
     * @param id
     * @return operation status of the remove
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseEntity<?> delete(Long id) {
        log.debug("method delete");
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Easily converts an address information into geographic coordinates.
     *
     * @param Address Model
     * @see Address
     */
    private void checkGeocoding(Address address) {
        if (isEmptyValue(address.getLatitude()) || isEmptyValue(address.getLongitude())) {
            try {
                LatLng location = geocodingClient.getLatLng(address.toString());
                if (location != null) {
                    address.setLatitude(location.lat);
                    address.setLongitude(location.lng);
                }
            } catch (Exception ex) {
                Logger.getLogger(AddressServiceImpl.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    /**
     * Utility method for check double values.
     * 
     * @param value
     * @return boolean condition if is empty or not
     */
    private static boolean isEmptyValue(Double value) {
        return value == null || value == 0;
    }

}





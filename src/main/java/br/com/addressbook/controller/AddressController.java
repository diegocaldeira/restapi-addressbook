package br.com.addressbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.addressbook.domain.Address;
import br.com.addressbook.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Endpoint for the address rest api, it contains basic operations for
 * get, create, update or delete addresses records from repository.
 * 
 * @author Diego Caldeira
 * @version 1.0 26 Jun 2021
 *
 */
@Api(tags = {"Address Book - REST API"})
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "/v1/addresses")
@Validated
public class AddressController {

    @Autowired
    private AddressService service;

    /**
     * Get all address ordering by street name.
     *
     * @return address list
     */
    @ApiOperation(value = "Get all addresses.", notes = "Get all addresses.")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Set<Address>> getAll() {
        Set<Address> all = service.findAll();
        return ResponseEntity.ok(all);
    }

    /**
     * Get a specific address by its identifier.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Get address by id.", notes = "Get specific address by its identifier number.")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Address> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    /**
     * Remove a specific address by its identifier.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Remove an address register.", notes = "Remove a specific address by its identifier number.")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> remove(@PathVariable Long id) {
        return service.delete(id);
    }

    /**
     * Creates a new address record.
     *
     * @param address
     * @return
     */
    @ApiOperation(value = "Save a new address.", notes = "Save a new address register.")
    @PostMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Address> save(@Validated @RequestBody Address address) {
        Address savedAddress = service.insert(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }

    /**
     * Updates an address with new data.
     *
     * @param id
     * @param address
     * @return
     */
    @ApiOperation(value = "Update an existing address.", notes = "Update an existing address register.")
    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Address> update(@PathVariable Long id, @Validated @RequestBody Address address) {
        return service.update(id, address);
    }

}












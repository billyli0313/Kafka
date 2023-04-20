package com.example.concur.controller;

import com.example.concur.entity.Address;
import com.example.concur.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;
    @PostMapping(value = "/create/address/table")
    public void createAddressTable(@RequestParam(value = "id")String id, @RequestParam(value = "street")String street,
                                   @RequestParam(value = "suite")String suite, @RequestParam(value = "city")String city,
                                   @RequestParam(value = "zipcode")String zipcode, @RequestParam(value = "geoID")String geoID)
            throws HttpClientErrorException {
        if(id.isEmpty()){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422),"Address ID is Empty!");
        }
        if(addressService.getAddressById(id) != null){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422),"Address exists, cannot create again!");
        }
        Address address = new Address();
        address.setId(id);
        address.setStreet(street);
        address.setSuite(suite);
        address.setCity(city);
        address.setZipcode(zipcode);
        address.setGeoID(geoID);
        addressService.saveAddress(address);
    }
    @PutMapping(value = "/update/address/table")
    public void updateAddressTable(@RequestParam(value = "id")String id, @RequestParam(value = "street")String street,
                                    @RequestParam(value = "suite")String suite, @RequestParam(value = "city")String city,
                                    @RequestParam(value = "zipcode")String zipcode, @RequestParam(value = "geoID")String geoID)
            throws HttpClientErrorException {
        if(id.isEmpty()){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422),"Address ID is Empty!");
        }
        Address address = addressService.getAddressById(id);
        if(address == null){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(404),"Please create address: "+ id + " first!");
        }
        address.setStreet(street);
        address.setSuite(suite);
        address.setCity(city);
        address.setZipcode(zipcode);
        address.setGeoID(geoID);
        addressService.saveAddress(address);
    }
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleHttpClientErrorException(HttpClientErrorException e){
        return "Bad entity. Error message: " + e.getMessage();
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleExceptions(Exception e){
        return "Sorry, there is an issue: " + e.getMessage();
    }
}

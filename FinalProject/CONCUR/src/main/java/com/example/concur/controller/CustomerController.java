package com.example.concur.controller;

import com.example.concur.entity.Customer;
import com.example.concur.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/create/customer")
    public void createCustomer(@RequestBody Customer customer)throws HttpClientErrorException{
        if(customer.getId().isEmpty()){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422),"Customer ID is Empty!");
        }
        if(customer.getAddress().getId().isEmpty()){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422),"Address ID is Empty!");
        }
        if(customer.getAddress().getGeography().getId().isEmpty()){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422),"Geography ID is Empty!");
        }
        if(customer.getCompany().getId().isEmpty()){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422),"Company ID is Empty!");
        }
        customerService.saveCustomer(customer);
    }
    @PostMapping(value = "/create/customer/table")
    public void createCustomerTable(@RequestParam(value = "id")String id,@RequestParam(value = "name")String name,
                                    @RequestParam(value = "username")String username,@RequestParam(value = "email")String email,
                                    @RequestParam(value = "addressID")String addressID,@RequestParam(value = "phone")String phone,
                                    @RequestParam(value = "website")String website,@RequestParam(value = "companyID")String companyID)
            throws HttpClientErrorException{
        if(id.isEmpty()){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422),"Customer ID is Empty!");
        }
        if(customerService.getCustomerByID(id) != null){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422),"Customer exists, cannot create again!");
        }
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setUsername(username);
        customer.setEmail(email);
        customer.setAddressID(addressID);
        customer.setPhone(phone);
        customer.setWebsite(website);
        customer.setCompanyID(companyID);
        customerService.saveCustomer(customer);
    }
    @GetMapping(value = "/get/customer")
    public Customer getCustomer(@RequestParam(value = "email")String email){
        if(email.isEmpty()){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422),"Email is Empty!");
        }
        return customerService.getCustomerByEmail(email);
    }
    @PutMapping(value = "/update/customer/table")
    public void updateCustomerTable(@RequestParam(value = "id")String id,@RequestParam(value = "name")String name,
                                    @RequestParam(value = "username")String username,@RequestParam(value = "email")String email,
                                    @RequestParam(value = "addressID")String addressID,@RequestParam(value = "phone")String phone,
                                    @RequestParam(value = "website")String website,@RequestParam(value = "companyID")String companyID)
            throws HttpClientErrorException{
        if(id.isEmpty()){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422),"Customer ID is Empty!");
        }
        Customer customer = customerService.getCustomerByID(id);
        if(customer == null){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(404),"Please create customer: "+ id + " first!");
        }
        customer.setName(name);
        customer.setUsername(username);
        customer.setEmail(email);
        customer.setAddressID(addressID);
        customer.setPhone(phone);
        customer.setWebsite(website);
        customer.setCompanyID(companyID);
        customerService.saveCustomer(customer);
    }
    @DeleteMapping(value = "/delete/byID")
    public void deleteCustomerById(@RequestParam(value = "id")String id) throws HttpClientErrorException{
        if(id.isEmpty()){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422),"Customer ID is Empty!");
        }
        Customer customer = customerService.getCustomerByID(id);
        if(customer == null){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(404),"Entity must not be null!");
        }
        customerService.deleteCustomer(customer);
    }
    @DeleteMapping(value = "/delete/byEmail")
    public void deleteCustomerByEmail(@RequestParam(value = "email")String email) throws HttpClientErrorException{
        if(email.isEmpty()){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(422),"Email is Empty!");
        }
        Customer customer = customerService.getCustomerByEmail(email);
        if(customer == null){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(404),"Entity must not be null!");
        }
        customerService.deleteCustomer(customer);
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


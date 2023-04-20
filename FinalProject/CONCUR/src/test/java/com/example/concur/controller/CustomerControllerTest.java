package com.example.concur.controller;

import com.example.concur.entity.Address;
import com.example.concur.entity.Company;
import com.example.concur.entity.Customer;
import com.example.concur.entity.Geography;
import com.example.concur.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest{
    @InjectMocks
    private CustomerController customerController;
    @Mock
    private CustomerService customerService;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void createCustomerTest(){
        Company company = new Company("10","Hoeger LLC","Centralized empowering task-force","target end-to-end models");
        Geography geography = new Geography("10","-38.2386","57.2232");
        Address address = new Address("10","Kattie Turnpike","Suite 198","Lebsackbury","31428-2261","10",geography);
        Customer customer = new Customer("10","Clementina DuBuque","Moriah.Stanton","Rey.Padberg@karina.biz","10","024-648-3804","ambrose.net","10",address,company);

        customerController.createCustomer(customer);
        verify(customerService, times(1)).saveCustomer(customer);
    }
    @Test(expected= HttpClientErrorException.class)
    public void CustomerIdExceptionTest() throws Exception{
        Company company = new Company("10","Hoeger LLC","Centralized empowering task-force","target end-to-end models");
        Geography geography = new Geography("10","-38.2386","57.2232");
        Address address = new Address("10","Kattie Turnpike","Suite 198","Lebsackbury","31428-2261","10",geography);
        Customer customer = new Customer("","Clementina DuBuque","Moriah.Stanton","Rey.Padberg@karina.biz","10","024-648-3804","ambrose.net","10",address,company);
        customerController.createCustomer(customer);
        when(customer.getId().isEmpty()).thenThrow(HttpClientErrorException.class);
        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            customerController.createCustomer(customer);
        });
    }
    @Test(expected= HttpClientErrorException.class)
    public void AddressIdExceptionTest() throws Exception{
        Company company = new Company("10","Hoeger LLC","Centralized empowering task-force","target end-to-end models");
        Geography geography = new Geography("10","-38.2386","57.2232");
        Address address = new Address("","Kattie Turnpike","Suite 198","Lebsackbury","31428-2261","10",geography);
        Customer customer = new Customer("10","Clementina DuBuque","Moriah.Stanton","Rey.Padberg@karina.biz","10","024-648-3804","ambrose.net","10",address,company);
        customerController.createCustomer(customer);
        when(customer.getAddress().getId().isEmpty()).thenThrow(HttpClientErrorException.class);
        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            customerController.createCustomer(customer);
        });
    }
    @Test(expected= HttpClientErrorException.class)
    public void GeographyIdExceptionTest() throws Exception{
        Company company = new Company("10","Hoeger LLC","Centralized empowering task-force","target end-to-end models");
        Geography geography = new Geography("","-38.2386","57.2232");
        Address address = new Address("10","Kattie Turnpike","Suite 198","Lebsackbury","31428-2261","10",geography);
        Customer customer = new Customer("10","Clementina DuBuque","Moriah.Stanton","Rey.Padberg@karina.biz","10","024-648-3804","ambrose.net","10",address,company);
        customerController.createCustomer(customer);
        when(customer.getAddress().getGeography().getId().isEmpty()).thenThrow(HttpClientErrorException.class);
        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            customerController.createCustomer(customer);
        });
    }
    @Test(expected= HttpClientErrorException.class)
    public void CompanyIdExceptionTest() throws Exception{
        Company company = new Company("","Hoeger LLC","Centralized empowering task-force","target end-to-end models");
        Geography geography = new Geography("10","-38.2386","57.2232");
        Address address = new Address("10","Kattie Turnpike","Suite 198","Lebsackbury","31428-2261","10",geography);
        Customer customer = new Customer("10","Clementina DuBuque","Moriah.Stanton","Rey.Padberg@karina.biz","10","024-648-3804","ambrose.net","10",address,company);
        customerController.createCustomer(customer);
        when(customer.getCompany().getId().isEmpty()).thenThrow(HttpClientErrorException.class);
        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            customerController.createCustomer(customer);
        });
    }
}

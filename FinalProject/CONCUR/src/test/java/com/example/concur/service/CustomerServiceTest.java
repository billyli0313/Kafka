package com.example.concur.service;

import com.example.concur.entity.Address;
import com.example.concur.entity.Company;
import com.example.concur.entity.Customer;
import com.example.concur.entity.Geography;
import com.example.concur.repository.JPACustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private JPACustomerRepository jpaCustomerRepository;
    @InjectMocks
    private CustomerService customerService;
    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void saveCustomerTest(){
        Company company = new Company("10","Hoeger LLC","Centralized empowering task-force","target end-to-end models");
        Geography geography = new Geography("10","-38.2386","57.2232");
        Address address = new Address("10","Kattie Turnpike","Suite 198","Lebsackbury","31428-2261","10",geography);
        Customer customer = new Customer("10","Clementina DuBuque","Moriah.Stanton","Rey.Padberg@karina.biz","10","024-648-3804","ambrose.net","10",address,company);

        customerService.saveCustomer(customer);
        verify(jpaCustomerRepository, times(1)).save(customer);
    }
    @Test
    public void deleteCustomerTest(){
        Company company = new Company("10","Hoeger LLC","Centralized empowering task-force","target end-to-end models");
        Geography geography = new Geography("10","-38.2386","57.2232");
        Address address = new Address("10","Kattie Turnpike","Suite 198","Lebsackbury","31428-2261","10",geography);
        Customer customer = new Customer("10","Clementina DuBuque","Moriah.Stanton","Rey.Padberg@karina.biz","10","024-648-3804","ambrose.net","10",address,company);

        customerService.deleteCustomer(customer);
        verify(jpaCustomerRepository, times(1)).delete(customer);
    }
    @Test
    public void getCustomerByEmailTest(){
        Company company = new Company("10","Hoeger LLC","Centralized empowering task-force","target end-to-end models");
        Geography geography = new Geography("10","-38.2386","57.2232");
        Address address = new Address("10","Kattie Turnpike","Suite 198","Lebsackbury","31428-2261","10",geography);
        Customer customer = new Customer("10","Clementina DuBuque","Moriah.Stanton","Rey.Padberg@karina.biz","10","024-648-3804","ambrose.net","10",address,company);
        Mockito.when(jpaCustomerRepository.findCustomerByEmail(anyString()))
                .thenReturn(Optional.of(customer));
        Customer customerRes = customerService.getCustomerByEmail("Rey.Padberg@karina.biz");
        assertThat(customerRes.getId()).isEqualTo("10");
    }
    @Test
    public void getCustomerByEmailNullTest(){
        Customer customer = null;
        Mockito.when(jpaCustomerRepository.findCustomerByEmail(anyString()))
                .thenReturn(Optional.ofNullable(customer));
        customerService.getCustomerByEmail("Rey.Padberg@karina.biz");
    }
    @Test
    public void getCustomerByIDTest(){
        Company company = new Company("10","Hoeger LLC","Centralized empowering task-force","target end-to-end models");
        Geography geography = new Geography("10","-38.2386","57.2232");
        Address address = new Address("10","Kattie Turnpike","Suite 198","Lebsackbury","31428-2261","10",geography);
        Customer customer = new Customer("10","Clementina DuBuque","Moriah.Stanton","Rey.Padberg@karina.biz","10","024-648-3804","ambrose.net","10",address,company);
        Mockito.when(jpaCustomerRepository.findById(anyString()))
                .thenReturn(Optional.of(customer));
        Customer customerRes = customerService.getCustomerByID("10");
        assertThat(customerRes.getEmail()).isEqualTo("Rey.Padberg@karina.biz");
    }
    @Test
    public void getCustomerByIDNullTest(){
        Customer customer = null;
        Mockito.when(jpaCustomerRepository.findCustomerByEmail(anyString()))
                .thenReturn(Optional.ofNullable(customer));
        customerService.getCustomerByID("10");
    }

}

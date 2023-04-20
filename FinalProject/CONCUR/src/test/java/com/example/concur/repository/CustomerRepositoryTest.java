package com.example.concur.repository;

import com.example.concur.entity.Address;
import com.example.concur.entity.Company;
import com.example.concur.entity.Customer;
import com.example.concur.entity.Geography;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {
    @Autowired
    private JPACustomerRepository jpaCustomerRepository;
    @Test
    public void saveCustomerTest_withEntityManager() {
        Company company = new Company("10","Hoeger LLC","Centralized empowering task-force","target end-to-end models");
        Geography geography = new Geography("10","-38.2386","57.2232");
        Address address = new Address("10","Kattie Turnpike","Suite 198","Lebsackbury","31428-2261","10",geography);
        Customer customer = new Customer("10","Clementina DuBuque","Moriah.Stanton","Rey.Padberg@karina.biz","10","024-648-3804","ambrose.net","10",address,company);

        jpaCustomerRepository.save(customer);
        Optional<Customer> result = jpaCustomerRepository.findById("10");
        Customer customerRes = new Customer();
        if (result.isPresent()) {
            customerRes = (Customer) result.get();
        }
        assertThat(customerRes.getEmail()).isEqualTo("Rey.Padberg@karina.biz");
    }
}

package com.example.cwt.repository;

import ch.qos.logback.core.joran.conditional.ThenOrElseActionBase;
import com.example.cwt.entity.Token;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JPATokenRepositoryTest {
    @Autowired
    private JPATokenRepository jpaTokenRepository;
    @Autowired
    private TestEntityManager entityManager;
    @Before
    public void setup() {
        entityManager.clear();
    }
    @Test
    public void saveTokenTest(){
        Token token = new Token("Rey.Padberg@karina.biz","EQeO3PaFXlweeCnjbNUqJXg",new Date(System.currentTimeMillis()));
        jpaTokenRepository.save(token);
        Optional<Token> result = jpaTokenRepository.findById("EQeO3PaFXlweeCnjbNUqJXg");
        Token tokenRes = new Token();
        if(result.isPresent()){
            tokenRes = (Token) result.get();
        }
        assertThat(tokenRes.getEmail()).isEqualTo("Rey.Padberg@karina.biz");
    }
    @Test
    public void findEmailTest(){
        Token token = new Token("Rey.Padberg@karina.biz","EQeO3PaFXlweeCnjbNUqJXg",new Date(System.currentTimeMillis()));
        entityManager.persist(token);
        entityManager.flush();
        Token tokenRes = jpaTokenRepository.findByEmail("Rey.Padberg@karina.biz");
        assertThat(tokenRes.getToken()).isEqualTo("EQeO3PaFXlweeCnjbNUqJXg");
    }
}

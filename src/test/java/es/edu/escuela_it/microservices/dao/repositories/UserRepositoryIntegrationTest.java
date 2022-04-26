package es.edu.escuela_it.microservices.dao.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import es.edu.escuela_it.microservices.dao.entities.UserEntity;

@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepostory;

    @Test
    public void WhenFindById_thenReturnUser() {

        UserEntity userEntity = new UserEntity(1, "Rafael");

        // Metodo sometido a test
        // El assert solo es una escusa para probar que este metodo guardo bien
        userRepostory.save(userEntity);

        // AssertJ
        // Permite hacer assert mas libre, robusto y avanzado que el assert de JUnit

        // Recupero el objeto con .findById() y que de el mismo objeto que se guardo
        Assertions.assertThat(userRepostory.findById(1))
                .isNotEmpty()
                .hasValue(userEntity);
    }
}
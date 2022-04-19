package es.edu.escuela_it.microservices;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
public class UserServiceImplTest{

    @BeforeAll
    static void setup() {
        log.info("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    public void setUpBefore() {
        log.info("@BeforeEach - executed before each test method");
    }

    @Test
    public void test1(){
        log.info("ejecutando test 1");
    }

    @Test
    public void test2(){
        log.info("ejecutando test 2");
    }

    @Test
    public void test3(){
        log.info("ejecutando test 3");
    }

    @AfterEach
    void tearDown() {
        log.info("@AfterEach - executed after each test method.");
    }

    @AfterAll
    static void done() {
        log.info("@AfterAll - executed after all test methods.");
    }
}


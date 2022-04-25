package es.edu.escuela_it.microservices.services;

import es.edu.escuela_it.microservices.dao.entities.UserEntity;
import es.edu.escuela_it.microservices.dao.repositories.UserRepository;
import es.edu.escuela_it.microservices.mappers.UserMapper;
import es.edu.escuela_it.microservices.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@Slf4j
public class UserServiceImplTest{

    // Clase sometida a test
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    private static List<UserEntity> userEntities;

    private static List<UserDTO> usersDtos;

    private static Pageable pageable;

    @BeforeAll
    static void setup() {

        PageRequest pageRequest = PageRequest.of(0, 10);
        pageable = pageRequest.next();

        userEntities = List.of(new UserEntity(1, "Miguel"), new UserEntity(1, "Rafael"), new UserEntity(1, "Alvaro"));
        usersDtos = List.of(new UserDTO(1, "Miguel"), new UserDTO(1, "Rafael"), new UserDTO(1, "Alvaro"));
    }

    @BeforeEach
    public void setUpBefore() {
        log.info("@BeforeAll - executes once before all test methods in this class");

        // Iniciamos el contexto de Mockito
        MockitoAnnotations.initMocks(this);

        // UserService a partir del Mock
        //userServiceImpl = new UserServiceImpl(userRepository, userMapper);

        Page<UserEntity> pagedUserEntities = new PageImpl<>(userEntities);

        log.info("Build Mock for userRepository");

        // SIMULAMOS UN COMPORTAMIENTO
        // Cuando se ejecute "userRepository.findAll()"
        // retorna una lista (fake) de UserEntities que se definio en el setup()
        // se ejecuta dentro de "userServiceImpl.listAllUsers()"
        // pero que no va a la base de datos, sino que va a la lista fake creada
        // la lista estará contenida en la variable "pageUsers" dentro del metodo "userServiceImpl.listAllUsers()"
        Mockito.when(userRepository.findAll(pageable)).thenReturn(pagedUserEntities);
        Mockito.when(userMapper.getUserDtos(userEntities)).thenReturn(usersDtos);

        log.info("@BeforeEach - executed before each test method");
    }

    @Test
    public void test_when_listUser_then_returnAllUsers(){
        log.info("Get all users");

        //Efective test method
        List<UserDTO> listAllUsers = userServiceImpl.listAllUsers(pageable);

        //Mockito verify
        // Tiene que haber llamado como minimo 1 vez al metodo "userRepository.findAll()"
        // Indica el escenario minimo indispenzable para que los test sean verdaderos
        verify(userRepository, atLeast(1)).findAll(pageable);
        verify(userMapper, atLeast(1)).getUserDtos(userEntities);

        //Asserts
        assertTrue(listAllUsers.size()>2);
        Optional<UserDTO> first = listAllUsers.stream().findFirst();
        assertEquals(first.get().getName(), "Developer Miguel"); // Evaluamos la logica de negocio
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


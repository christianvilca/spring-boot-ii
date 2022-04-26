package es.edu.escuela_it.microservices.services;

import es.edu.escuela_it.microservices.dao.entities.UserEntity;
import es.edu.escuela_it.microservices.dao.repositories.UserRepository;
import es.edu.escuela_it.microservices.mappers.UserMapper;
import es.edu.escuela_it.microservices.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
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

    private static PodamFactory factory;

    private final static int EXPECTED_SIZE = 99;

    private static List<UserEntity> userEntities;

    private static List<UserDTO> usersDtos;

    private static Pageable pageable;

    @BeforeAll
    static void setup() {

        // Inicializacion de la libreria
        factory = new PodamFactoryImpl();
        factory.getStrategy().setMemoization(false);

        userEntities = new ArrayList<UserEntity>();
        usersDtos = new ArrayList<UserDTO>();

        for (int i = 0; i < 100; i++) {
            // Manufacturacion del objeto
            // Completara todos los datos de UserEntity
            // Buscara getter y setter e incorpodara informacion sin sentido, pero valido para las pruebas
            UserEntity userEntity = factory.manufacturePojoWithFullData(UserEntity.class);
            userEntities.add(userEntity);

            // Ahorra recursos computacionales minimos, pero recomendada
            // Si el trace estuviera a nivel error, porlo que info no va llegar a ejecutarse
            // Pero si va llegar a ejecurarse ""User:  {}" + userEntity.toString()"
            // Es mejor ejecutar lo de esta forma: ""User:  {}", userEntity.toString()"
            // Por que asi suministramos 2 parametros y es dentro de info que se encarga de hacer esa conjuncion
            log.info("User:  {}" + userEntity.toString());

            UserDTO userDto = factory.manufacturePojoWithFullData(UserDTO.class);
            usersDtos.add(userDto);

            log.info("User:  {}" + userDto.toString());
        }

        PageRequest pageRequest = PageRequest.of(0, 10);
        pageable = pageRequest.next();

    }

    /**
     * 00:06:45.006 [main] INFO  e.e.e.m.services.UserServiceImplTest - User:  {}
     * UserDTO(
     *          id=1067267737,
     *          name=icScBAV1uS,
     *          lastname=dK_Fcw_sR7BjWHdk2,
     *          email=null,
     *          active=true,
     *          birthDay=null,
     *          cif=null,
     *          title=Xk3sVkzNQe,
     *          body=NkpQyvZ5Kp)
     */

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

    @DisplayName("Test Get list users Service") // Nombre que aparecera en el reporte de test
    @Order(1)   // Si tengo varios test, indico el orden
    //@Disabled   // Desabilita o ignora el test
    @EnabledOnOs(OS.LINUX)  // Ejecutar el test, siempre que este ejecutandose sobre la plataforma LINUX
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
        assertTrue(listAllUsers.size()>EXPECTED_SIZE);
        Optional<UserDTO> first = listAllUsers.stream().findFirst();
        assertEquals(first.get().getName(), "Developer Miguel"); // Evaluamos la logica de negocio
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    public final void sum(int i) {
        log.info("valor i " + i);
        assertTrue(i > 0);
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


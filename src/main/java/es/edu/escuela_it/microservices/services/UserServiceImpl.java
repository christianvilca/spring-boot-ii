package es.edu.escuela_it.microservices.services;

import es.edu.escuela_it.microservices.dao.entities.UserEntity;
import es.edu.escuela_it.microservices.dao.repositories.UserRepository;
import es.edu.escuela_it.microservices.mappers.UserMapper;
import es.edu.escuela_it.microservices.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("BD") // Servicio que tiene un alias
@ConditionalOnProperty(prefix = "app", name = "edition", havingValue = "community") // Aqui lo elije por un archivo .properties
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    public Optional<UserDTO> getUserById(Integer id) {
        Optional<UserEntity> optUserDto = userRepository.findById(id);
        UserEntity userEntity = optUserDto.get();

        UserDTO userDTO = userMapper.getUserDto(userEntity);

        return Optional.of(userDTO);
    }

    @Override
    public List<UserDTO> listAllUsers(Pageable pageable) {
        // List<UserDTO> users = userRepository.findByAgeLessThan(22);
        Page<UserEntity> pageUsers = userRepository.findAll(pageable);

        List<UserEntity> usersEntities = pageUsers.getContent();

        List<UserDTO> userDtos = userMapper.getUserDtos(usersEntities);

        return userDtos;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        UserEntity userEntity = userMapper.getUserEntity(userDTO);  // Transformacion
        userEntity = userRepository.save(userEntity);               // Ejecucion
        userDTO = userMapper.getUserDto(userEntity);                // Transformacion

        return userDTO;
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}

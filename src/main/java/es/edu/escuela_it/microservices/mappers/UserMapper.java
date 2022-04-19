package es.edu.escuela_it.microservices.mappers;

import es.edu.escuela_it.microservices.dao.entities.UserEntity;
import es.edu.escuela_it.microservices.model.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring") // Permite ser inyectado con @Autowired
public interface UserMapper {

    // Metodo que devuelva un UserDTO a partir de un UserEntity
    public UserDTO getUserDto(UserEntity userEntity);

    public UserEntity getUserEntity(UserDTO userDTO);

    public List<UserDTO> getUserDtos(List<UserEntity> userEntity);

}
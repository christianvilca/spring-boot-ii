package es.edu.escuela_it.microservices.dao.repositories;

import es.edu.escuela_it.microservices.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Integer> {

}
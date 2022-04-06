package es.edu.escuela_it.microservices.dao.repositories;

import es.edu.escuela_it.microservices.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Integer> {

    public List<UserDTO> findByAgeLessThan(int age);
    public List<UserDTO> findByAgeGreaterThanEqual(int age);
    public List<UserDTO> findByNameLike(String name);
    public List<UserDTO> findByNameContaining(String name); // El '% LIKE %' de SQL

    // Usuario que tengan el mismo nombre, y tenga un rango de edad
    // findAllUsersBetweenAgeAndName("nombre", 18, 40)
    // nativeQuery = true -> Query SQL
    @Query(value="select * from ms_users where name = ?1 and edad >= ?2 and edad <= ?3", nativeQuery = true)
    public List<UserDTO> findAllUsersBetweenAgeAndName(String name, int ageBegin, int ageEnd);
}
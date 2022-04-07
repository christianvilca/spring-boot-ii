package es.edu.escuela_it.microservices.dao.repositories;

import es.edu.escuela_it.microservices.dao.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    public List<UserEntity> findByAgeLessThan(int age);
    public List<UserEntity> findByAgeGreaterThanEqual(int age);
    public List<UserEntity> findByNameLike(String name);
    public List<UserEntity> findByNameContaining(String name); // El '% LIKE %' de SQL

    // Usuario que tengan el mismo nombre, y tenga un rango de edad
    // findAllUsersBetweenAgeAndName("nombre", 18, 40)
    // nativeQuery = true -> Query SQL
    @Query(value="select * from ms_users where name = ?1 and edad >= ?2 and edad <= ?3", nativeQuery = true)
    public List<UserEntity> findAllUsersBetweenAgeAndName(String name, int ageBegin, int ageEnd);
}
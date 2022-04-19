package es.edu.escuela_it.microservices.dao.entities;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true) // Lamamos a los metodos del padre
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "ms_users") // Se mapea con una tabla de la BD
public class UserEntity extends RepresentationModel<UserEntity> {

    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NonNull
    private String name;

    @Column(name = "last_name")
    private String lastname;

    private int age;

}

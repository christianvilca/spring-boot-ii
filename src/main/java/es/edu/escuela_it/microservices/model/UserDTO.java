package es.edu.escuela_it.microservices.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDTO extends RepresentationModel<UserDTO> {

    @NonNull
    private Integer id;
    @NonNull
    private String name;
    private String lastname;

    @ToString.Exclude
    private int age;

}

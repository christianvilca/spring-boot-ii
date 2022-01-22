package es.edu.escuela_it.microservices.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;

@Data
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDTO extends RepresentationModel<UserDTO> {

    @NonNull
    private Integer id;
    @NonNull
    private String name;

    @NotNull
    private String lastname;

    @ToString.Exclude
    private int age;

}

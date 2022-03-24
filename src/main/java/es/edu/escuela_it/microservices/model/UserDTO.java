package es.edu.escuela_it.microservices.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true) // Lamamos a los metodos del padre
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@ApiModel(description = "System user")
// RepresentationModel -> Objeto de HATEOAS
public class UserDTO extends RepresentationModel<UserDTO> {

    @NonNull
    // Estas anotaciones repercuten en las interfaces graficas del swagger
    @ApiModelProperty(notes = "Unique identifier of the User.", example = "1", required = true, position = 0)
    private Integer id;
    @NonNull
    private String name;

    @NotNull
    private String lastname;

    @ToString.Exclude
    private int age;

}

package es.edu.escuela_it.microservices.model;

import es.edu.escuela_it.microservices.validators.CIF;
import es.edu.escuela_it.microservices.validators.GroupValidatorOnCreate;
import es.edu.escuela_it.microservices.validators.GroupValidatorOnUpdate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@EqualsAndHashCode(callSuper = true) // Lamamos a los metodos del padre
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@ApiModel(description = "System user")
// RepresentationModel -> Objeto de HATEOAS
@Entity(name = "ms_users") // Se mapea con una tabla de la BD
public class UserDTO extends RepresentationModel<UserDTO> {

    @NonNull
    // Estas anotaciones repercuten en las interfaces graficas del swagger
    @NotNull
    @ApiModelProperty(notes = "Unique identifier of the User.", example = "1", required = true, position = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NonNull
    @NotBlank
    private String name;

    @NotNull
    @Size(min = 6, max = 20)
    @Column(name = "last_name")
    private String lastname;

    @Positive
    @Min(18)
    @Max(90)
    @ToString.Exclude
    private int age;

    @Email
    @ApiModelProperty(example = "string@gmail.com")
    private String email;

    @AssertTrue(message = "{app.field.active.error}", groups = GroupValidatorOnCreate.class)
    @AssertFalse(message = "Debe ser falso", groups = GroupValidatorOnUpdate.class)
    private boolean active;

    // Fechas que sean antes de la actual
    @Past (message = "{app.field.birth_day.error}")
    @ApiModelProperty(example = "1982-01-02")
    private LocalDate birthDay;

    @CIF // Podemos tambien poner mensaje
    private String cif;

    private String title;

    private String body;
}

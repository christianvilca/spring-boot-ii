package es.edu.escuela_it.microservices.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class UserDTO {

    @NonNull
    private Integer id;
    @NonNull
    private String name;
    private String lastname;

    @ToString.Exclude
    private int age;

}

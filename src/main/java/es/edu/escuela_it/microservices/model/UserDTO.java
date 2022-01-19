package es.edu.escuela_it.microservices.model;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDTO {

    @NonNull
    private Integer id;
    @NonNull
    private String name;
    private String lastname;

    @ToString.Exclude
    private int age;

}

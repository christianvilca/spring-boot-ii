package es.edu.escuela_it.microservices.dao.entities;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class AccountEntity {

    private Integer id;

    @NonNull
    private String name;

}

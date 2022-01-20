package es.edu.escuela_it.microservices.model;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class AccountDTO {

    private Integer id;

    @NonNull
    private String name;

}

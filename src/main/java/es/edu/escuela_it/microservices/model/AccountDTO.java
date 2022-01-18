package es.edu.escuela_it.microservices.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class AccountDTO {

    private Integer id;

    private String name;

}

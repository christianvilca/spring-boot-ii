package es.edu.escuela_it.microservices.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class CountryEntity {

    private String isoCode;

    private String name;

    private String flag;

}

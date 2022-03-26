package es.edu.escuela_it.microservices.client;

import es.edu.escuela_it.microservices.model.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// Clase encargada de implementar el servicio y buscar el UserDTO desde un servidor remoto

@Service
public class UserClientImpl implements UserClient {

    @Override
    public UserDTO getUser(Integer id) {

        RestTemplate restTemplate = new RestTemplate(); // recomendado crear el objeto y llamarlo por autowired
        String fooResourceUrl = "https://jsonplaceholder.typicode.com/posts/1"; // recuperacion de un usuario de un servicio ficticio
        UserDTO user = restTemplate.getForObject(fooResourceUrl, UserDTO.class);
        return user;
    }
}
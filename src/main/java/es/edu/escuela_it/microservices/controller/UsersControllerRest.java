package es.edu.escuela_it.microservices.controller;

import es.edu.escuela_it.microservices.model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UsersControllerRest {

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id){ // Otra forma @PathVariable("id") Integer idUser
        System.out.println("Recovery user by id");
        UserDTO userDTO = null; //new UserDTO(1, "Christian");
//        userDTO.setAge(35);
//        userDTO.setLastname("Vilca");

        if (userDTO == null) {
            return ResponseEntity.notFound().build();
        }

        // Spring traduce el objeto java a json
        // Jackson (libreria) esta trabajando para la serializacion y descerializacion
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> listAllUsers( @RequestParam(required = false) String name,
                                       @RequestParam(required = false) String lastName,
                                       @RequestParam(required = false) Integer age) {

        List<UserDTO> list = List.of(new UserDTO(1, "Rafael"),
                new UserDTO(2, "Miguel"),
                new UserDTO(3, "Alvaro"));

        list = list.stream().filter(u -> u.getName().contains(name)).collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) throws MalformedURLException {
        System.out.println("Recovery user by id " + userDTO.getName());

        // Recupera la ruta actual
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDTO.getId())
                .toUri();

        return ResponseEntity.created(location).build() ;
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        System.out.println("Updating data");
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        System.out.println("Delete user by id");
        return ResponseEntity.ok(null);
    }
}

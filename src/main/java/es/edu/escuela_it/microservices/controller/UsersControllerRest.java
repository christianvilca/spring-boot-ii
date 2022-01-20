package es.edu.escuela_it.microservices.controller;

import es.edu.escuela_it.microservices.model.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UsersControllerRest {

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable Integer id){ // Otra forma @PathVariable("id") Integer idUser
        System.out.println("Recovery user by id");
        UserDTO userDTO = new UserDTO(1, "Christian");
        userDTO.setAge(35);
        userDTO.setLastname("Vilca");

        // Spring traduce el objeto java a json
        // Jackson (libreria) esta trabajando para la serializacion y descerializacion
        return userDTO;
    }

    @GetMapping("/users")
    public List<UserDTO> listAllUsers( @RequestParam(required = false) String name,
                                       @RequestParam(required = false) String lastName,
                                       @RequestParam(required = false) Integer age) {

        List<UserDTO> list = List.of(new UserDTO(1, "Rafael"),
                new UserDTO(2, "Miguel"),
                new UserDTO(3, "Alvaro"));

        list = list.stream().filter(u -> u.getName().contains(name)).collect(Collectors.toList());

        return list;
    }

    @PostMapping("/users")
    public String createUser(@RequestBody UserDTO userDTO){
        System.out.println("Recovery user by id " + userDTO.getName());
        return "http://localhost:8080/users/" + userDTO.getId();
    }

    @PutMapping("/users")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        System.out.println("Updating data");
        return userDTO;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        System.out.println("Delete user by id");
    }
}
